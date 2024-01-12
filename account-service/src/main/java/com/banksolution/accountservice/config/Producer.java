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

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(Transaction transaction) throws JsonProcessingException {
        String transactionAsMessage = objectMapper.writeValueAsString(transaction);
        kafkaTemplate.send(orderTopic, transactionAsMessage);
        return "message sent";
    }
}