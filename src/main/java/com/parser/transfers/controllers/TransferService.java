package com.parser.transfers.controllers;

import com.parser.transfers.domain.Account;
import com.parser.transfers.domain.Transfer;
import com.parser.transfers.exception.TransferNotPossibleException;
import com.parser.transfers.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import static com.parser.transfers.exception.TransferNotPossibleException.CAUSES.DESTINATION_NOT_FOUND;
import static com.parser.transfers.exception.TransferNotPossibleException.CAUSES.SOURCE_NOT_FOUND;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean validateTransfer(Transfer newTransfer) throws RuntimeException {
        Account source = accountRepository.getOne(newTransfer.getSource());
        Account destination = accountRepository.getOne(newTransfer.getDestination());
        if (source == null) {
            throw new TransferNotPossibleException(SOURCE_NOT_FOUND, newTransfer.getSource());
        }
        if (destination == null) {
            throw new TransferNotPossibleException(DESTINATION_NOT_FOUND, newTransfer.getDestination());
        }

        return true;
    }

}
