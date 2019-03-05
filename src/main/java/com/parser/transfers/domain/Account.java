package com.parser.transfers.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.iban4j.Iban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private Long accountId;
    private String iban;
    private BigDecimal balance;
    private Currency currency;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime openDate;
    private Boolean active;

    public Account() {
        this.iban = generateIban();
        this.openDate = LocalDateTime.now();
        this.active = true;
    }

    public Account(BigDecimal balance, Currency currency) {
        super();
        this.balance = balance;
        this.currency = currency;
    }

    private String generateIban() {
        return Iban.random().toFormattedString();
    }
}
