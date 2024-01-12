package com.banksolution.transactionservice.controller;

import com.banksolution.transactionservice.service.TransactionRecepientService;
import com.banksolution.transactionservice.service.TransactionSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final TransactionRecepientService transactionRecepientService;
    private final TransactionSenderService transactionSenderService;

    @GetMapping("/received/day/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForDay(@PathVariable UUID id){
        return transactionRecepientService.getTransactionsForDay(id);
    }

    @GetMapping("/received/month/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForMonth(@PathVariable UUID id){
        return transactionRecepientService.getTransactionsForMonth(id);
    }

    @GetMapping("/received/week/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForWeek(@PathVariable UUID id){
        return transactionRecepientService.getTransactionsForWeek(id);
    }

    @GetMapping("/sent/day/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForDaySent(@PathVariable UUID id){
        return transactionSenderService.getTransactionsForDay(id);
    }

    @GetMapping("/sent/month/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForMonthSent(@PathVariable UUID id){
        return transactionSenderService.getTransactionsForMonth(id);
    }

    @GetMapping("/sent/week/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTransactionsForWeekSent(@PathVariable UUID id){
        return transactionSenderService.getTransactionsForWeek(id);
    }

}
