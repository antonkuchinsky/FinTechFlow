package com.banksolution.transactionservice.service.impl;

import com.banksolution.transactionservice.exception.InvalidDataException;
import com.banksolution.transactionservice.repository.TransactionRepository;
import com.banksolution.transactionservice.service.TransactionRecepientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionRecepientServiceImpl implements TransactionRecepientService {
    private final TransactionRepository transactionRepository;

    @Override
    public BigDecimal getTransactionsForMonth(UUID id) {
        return transactionRepository.getTotalTransactionsForMonthForRecepient(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }

    @Override
    public BigDecimal getTransactionsForWeek(UUID id) {
        return transactionRepository.getTotalTransactionsForWeekForRecepient(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }

    @Override
    public BigDecimal getTransactionsForDay(UUID id) {
        return transactionRepository.getTotalTransactionsForDayForRecepient(id)
                .orElseThrow(()->new InvalidDataException("Account with this id not found", "Account not found"));
    }
}
