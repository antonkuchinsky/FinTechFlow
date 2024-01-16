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

    @Value("${topic_refill_or_write-off_balance.name}")
    private String transactionalRefillOrWriteOffBalance;


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public String sendTransactionsOfAccountsMessage(Transaction transaction) throws JsonProcessingException {
        String transactionAsMessage = objectMapper.writeValueAsString(transaction);
        kafkaTemplate.send(transactionalOfAccount, transactionAsMessage);
        return "message sent";
    }

    public String sendTransactionsRefillOrWriteOffBalanceMessage(Transaction transaction) throws JsonProcessingException {
        String transactionAsMessage = objectMapper.writeValueAsString(transaction);
        kafkaTemplate.send(transactionalRefillOrWriteOffBalance, transactionAsMessage);
        return "message sent";
    }
}