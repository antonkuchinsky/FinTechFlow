package com.banksolution.transactionservice.service.impl;

import com.banksolution.transactionservice.model.Transaction;
import com.banksolution.transactionservice.repository.TransactionRepository;
import com.banksolution.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(Transaction transaction) {
    transactionRepository.save(transaction);
    }
}
