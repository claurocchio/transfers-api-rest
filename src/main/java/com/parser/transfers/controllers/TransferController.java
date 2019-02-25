package com.parser.transfers.controllers;

import com.parser.transfers.domain.Transfer;
import com.parser.transfers.domain.Transfer;
import com.parser.transfers.exception.AccountNotFoundException;
import com.parser.transfers.repositories.TransferRepository;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TransferController {

    private final TransferRepository repository;

    public TransferController(TransferRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transfer")
    public Resources<Resource<Transfer>> all() {
        List<Resource<Transfer>> transfers = repository.findAll().stream()
                .map(Transfer -> new Resource<>(Transfer,
                        linkTo(methodOn(TransferController.class).one(Transfer.getId())).withSelfRel(),
                        linkTo(methodOn(TransferController.class).all()).withRel("Transfer")))
                .collect(Collectors.toList());

        return new Resources<>(transfers, linkTo(methodOn(TransferController.class).all()).withSelfRel());
    }

    @GetMapping("/transfer/{id}")
    Resource<Transfer> one(@PathVariable Long id) {

        Transfer Transfer = repository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        return new Resource<>(Transfer,
                linkTo(methodOn(TransferController.class).one(id)).withSelfRel(),
                linkTo(methodOn(TransferController.class).all()).withRel("transfer"));
    }
}
