package com.banksolution.transactionservice.repository;

import com.banksolution.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<UUID, Transaction> {
}
