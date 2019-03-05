package com.parser.transfers.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long source;
    private Long destination;
    private BigDecimal amount;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime timestamp;

    public Transfer() {
    }

    public Transfer(Long destination, BigDecimal amount, String description, LocalDateTime timestamp) {
        this.destination = destination;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }
}
