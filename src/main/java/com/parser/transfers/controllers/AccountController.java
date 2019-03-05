package com.parser.transfers.controllers;

import com.parser.transfers.domain.Account;
import com.parser.transfers.exception.AccountNotFoundException;
import com.parser.transfers.repositories.AccountRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AccountController {

    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/account")
    public Resources<Resource<Account>> all(){
        List<Resource<Account>> accounts = repository.findAll().stream()
                .map(account -> new Resource<>(account,
                        linkTo(methodOn(AccountController.class).one(account.getAccountId())).withSelfRel(),
                        linkTo(methodOn(AccountController.class).all()).withRel("account")))
                .collect(Collectors.toList());

        return new Resources<>(accounts,linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }

    @GetMapping("/account/{id}")
    Resource<Account> one(@PathVariable Long id) {

        Account account = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return new Resource<>(account,
                linkTo(methodOn(AccountController.class).one(id)).withSelfRel(),
                linkTo(methodOn(AccountController.class).all()).withRel("account"));
    }

    @PutMapping("/account/{id}")
    Resource<Account> add(@RequestBody Account newAccount, @PathVariable Long id){
        return repository.findById(id)
                .map(account -> {
                    account.setCurrency(newAccount.getCurrency());
                    account.setBalance(newAccount.getBalance());
                    return new Resource<>(repository.save(account),
                            linkTo(methodOn(AccountController.class).one(id)).withSelfRel(),
                            linkTo(methodOn(AccountController.class).all()).withRel("account"));
                })
                .orElseGet(() -> {
                    newAccount.setAccountId(id);
                    return new Resource<>(repository.save(newAccount),
                            linkTo(methodOn(AccountController.class).one(id)).withSelfRel(),
                            linkTo(methodOn(AccountController.class).all()).withRel("account"));
                });
    }
}
