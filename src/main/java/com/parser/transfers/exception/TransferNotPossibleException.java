package com.parser.transfers.exception;

public class TransferNotPossibleException extends RuntimeException {
    public enum CAUSES {
        SOURCE_NOT_FOUND,
        DESTINATION_NOT_FOUND,
        ACCOUNT_NOT_ACTIVE
    }

    public TransferNotPossibleException(CAUSES cause, Long id) {
        switch (cause) {
            case SOURCE_NOT_FOUND:
                throw new RuntimeException("Could not find source account id: " + id);
            case DESTINATION_NOT_FOUND:
                throw new RuntimeException("Could not find destination account id: " + id);
            case ACCOUNT_NOT_ACTIVE:
                throw new RuntimeException("The account with id: " + id + " is not active");
        }
    }
}
