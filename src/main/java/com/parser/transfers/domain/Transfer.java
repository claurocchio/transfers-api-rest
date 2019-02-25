package com.parser.transfers.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Transfer {

    @GeneratedValue
    @Id
    private Long id;
    private Long source;
    private Long destination;
    private BigDecimal amount;
    private String description;
    private Timestamp timestamp;

    public Transfer() {
    }

    public Transfer(Long destination, BigDecimal amount, String description, Timestamp timestamp) {
        this.destination = destination;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }
}
