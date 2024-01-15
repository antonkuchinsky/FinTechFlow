package com.banksolution.accountservice.config;

import com.banksolution.accountservice.dto.transactional.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {


    @Value("${topic_of_accounts.name}")
    private String transactionalOfAccount;

    @Value("${topic_refill_balance.name}")
    private String transactionalRefillBalance;


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public String sendTransactionsOfAccountsMessage(Transaction transaction) throws JsonProcessingException {
        String transactionAsMessage = objectMapper.writeValueAsString(transaction);
        kafkaTemplate.send(transactionalOfAccount, transactionAsMessage);
        return "message sent";
    }

    public String sendTransactionsRefillBalanceMessage(Transaction transaction) throws JsonProcessingException {
        String transactionAsMessage = objectMapper.writeValueAsString(transaction);
        kafkaTemplate.send(transactionalRefillBalance, transactionAsMessage);
        return "message sent";
    }
}