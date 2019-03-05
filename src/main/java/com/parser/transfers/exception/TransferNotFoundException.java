package com.parser.transfers.exception;

public class TransferNotFoundException extends RuntimeException {
    public TransferNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
