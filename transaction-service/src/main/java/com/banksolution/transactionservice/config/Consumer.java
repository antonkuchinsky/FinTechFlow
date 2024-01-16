package com.banksolution.transactionservice.config;

import com.banksolution.transactionservice.model.Transaction;
import com.banksolution.transactionservice.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private static final String regTopicOfAccount = "${topic_of_accounts.name}";
    private static final String regTopicOfRefillBalance="${topic_refill_balance.name}";

    private final ObjectMapper objectMapper;
    private final TransactionService transactionService;

    @KafkaListener(topics = regTopicOfAccount)
    public void consumeMessageOfAccounts(String message) throws JsonProcessingException {

        var transaction = objectMapper.readValue(message, Transaction.class);
        transactionService.saveTransaction(transaction);
    }

    @KafkaListener(topics = regTopicOfRefillBalance)
    public void consumeMessage(String message) throws JsonProcessingException {

        var transaction = objectMapper.readValue(message, Transaction.class);
        transactionService.saveTransaction(transaction);
    }
}
