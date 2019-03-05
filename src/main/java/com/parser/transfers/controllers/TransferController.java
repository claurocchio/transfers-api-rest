package com.parser.transfers.controllers;

import com.parser.transfers.domain.Transfer;
import com.parser.transfers.exception.TransferNotFoundException;
import com.parser.transfers.repositories.AccountRepository;
import com.parser.transfers.repositories.TransferRepository;
import com.parser.transfers.resources.TransferAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TransferController {

    private final TransferRepository repository;
    private final TransferAssembler assembler;
    private final TransferService transferService;

    public TransferController(TransferRepository repository, TransferAssembler transferAssembler, TransferService transferService) {
        this.repository = repository;
        this.assembler = transferAssembler;
        this.transferService = transferService;
    }

    @GetMapping("/transfer")
    public Resources<Resource<Transfer>> all() {
        List<Resource<Transfer>> transfers = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(transfers, linkTo(methodOn(TransferController.class).all()).withSelfRel());
    }

    @GetMapping("/transfer/{id}")
    public Resource<Transfer> one(@PathVariable Long id) {

        Transfer transfer = repository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

        return assembler.toResource(transfer);
    }

    @PostMapping("/transfer/{id}")
    Resource<Transfer> add(@RequestBody Transfer newTransfer) {
        try{
            transferService.validateTransfer(newTransfer);
            repository.save(newTransfer);
            return assembler.toResource(newTransfer);
        } catch (Exception e){
            throw e;
        }

    }

    @DeleteMapping("/transfer/{id}")
    ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        Optional<Transfer> transfer = repository.findById(id);

        if (!transfer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
