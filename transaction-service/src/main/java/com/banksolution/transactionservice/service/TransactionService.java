package com.banksolution.transactionservice.service;

import com.banksolution.transactionservice.model.Transaction;

public interface TransactionService {
    void saveTransaction(Transaction transaction);
}
