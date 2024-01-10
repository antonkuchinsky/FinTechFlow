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

    private static final String regTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final TransactionService transactionService;

    @KafkaListener(topics = regTopic)
    public void consumeMessage(String message) throws JsonProcessingException {

        var transaction = objectMapper.readValue(message, Transaction.class);
        transactionService.saveTransaction(transaction);
    }
}
