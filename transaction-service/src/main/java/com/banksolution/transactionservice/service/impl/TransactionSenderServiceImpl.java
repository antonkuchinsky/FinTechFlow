package com.banksolution.transactionservice.service.impl;

import com.banksolution.transactionservice.exception.InvalidDataException;
import com.banksolution.transactionservice.repository.TransactionRepository;
import com.banksolution.transactionservice.service.TransactionSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionSenderServiceImpl implements TransactionSenderService {
    private final TransactionRepository transactionRepository;

    @Override
    public BigDecimal getTransactionsForMonth(UUID id) {
        return transactionRepository.getTotalTransactionsForMonthForSender(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }

    @Override
    public BigDecimal getTransactionsForWeek(UUID id) {
        return transactionRepository.getTotalTransactionsForWeekForSender(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }

    @Override
    public BigDecimal getTransactionsForDay(UUID id) {
        return transactionRepository.getTotalTransactionsForDayForSender(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }
}