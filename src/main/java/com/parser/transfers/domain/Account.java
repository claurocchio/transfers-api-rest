package com.parser.transfers.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;

@Data
@Entity
public class Account {

    @GeneratedValue
    @Id
    private Long accountId;
    private String iban;
    private BigDecimal balance;
    private Currency currency;
    private Date openDate;
    private Boolean active;

    public Account() {
    }

    public Account(BigDecimal balance, Currency currency) {
        this.iban = generateIban();
        this.balance = balance;
        this.currency = currency;
        this.openDate = openDate;
        this.active = active;
    }

    private String generateIban() {

    }
}
