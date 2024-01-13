package com.banksolution.transactionservice.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionSenderService {
    BigDecimal getTransactionsForMonth(UUID id);
    BigDecimal getTransactionsForWeek(UUID id);
    BigDecimal getTransactionsForDay(UUID id);
}
