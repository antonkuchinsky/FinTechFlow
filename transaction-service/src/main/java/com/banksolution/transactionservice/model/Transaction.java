package com.banksolution.transactionservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name="transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private UUID senderId;
    private UUID recepientId;
    private BigDecimal sum;
    private String currency;
    @Column(name="date_transaction")
    private LocalDate date;
}
