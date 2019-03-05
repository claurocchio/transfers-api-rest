package com.parser.transfers.resources;

import com.parser.transfers.controllers.TransferController;
import com.parser.transfers.domain.Transfer;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TransferAssembler implements ResourceAssembler<Transfer, Resource<Transfer>> {
    @Override
    public Resource<Transfer> toResource(Transfer transfer) {
        return new Resource<>(transfer,
                linkTo(methodOn(TransferController.class).one(transfer.getId())).withSelfRel(),
                linkTo(methodOn(TransferController.class).all()).withRel("transfers"));
    }
}
