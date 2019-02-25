package com.parser.transfers;

import com.parser.transfers.domain.Account;
import com.parser.transfers.repositories.AccountRepository;
import com.parser.transfers.repositories.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {


    private CsvUtils csvUtils;

    public LoadDatabase(CsvUtils csvUtils) {
        this.csvUtils = csvUtils;
    }

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, TransferRepository transferRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.saveAll(csvUtils.loadObjectList(Account.class, "accounts.csv")));
            log.info("Preloading " + transferRepository.saveAll(csvUtils.loadObjectList(Transfer.class, "transfers.csv")));
        };
    }



}
