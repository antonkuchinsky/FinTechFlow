package com.banksolution.accountservice.service.impl;

import com.banksolution.accountservice.config.Producer;
import com.banksolution.accountservice.dto.transactional.BalanceOperationDto;
import com.banksolution.accountservice.dto.transactional.Transaction;
import com.banksolution.accountservice.dto.transactional.TransferMoneyDto;
import com.banksolution.accountservice.exception.InsufficientFundsException;
import com.banksolution.accountservice.exception.InvalidDataException;
import com.banksolution.accountservice.repository.AccountRepository;
import com.banksolution.accountservice.service.TransactionalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionalServiceImpl implements TransactionalService {
    private final AccountRepository accountRepository;
    private final Producer producer;

    @Override
    @Transactional
    public void transferFundsBetweenAccounts(TransferMoneyDto transferMoneyDto) {
        var sender = accountRepository.findById(transferMoneyDto.senderId());
        var senderBalance = sender.get().getBalance();

        var recepient = accountRepository.findById(transferMoneyDto.recepientId());
        var recepientBalance = recepient.get().getBalance();

        if (senderBalance.subtract(transferMoneyDto.money()).compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("There are insufficient funds in the sender's account", "Insufficient funds");
        } else {
            sender.get().setBalance(senderBalance.subtract(transferMoneyDto.money()));
            recepient.get().setBalance(recepientBalance.add(transferMoneyDto.money()));
            accountRepository.save(sender.get());
            accountRepository.save(recepient.get());

            try {
                producer.sendTransactionsOfAccountsMessage(new Transaction(sender.get().getId(),
                        recepient.get().getId(),
                        transferMoneyDto.money(),
                        transferMoneyDto.currency(),
                        LocalDate.now()));
            } catch (JsonProcessingException e) {
                throw new InternalServerErrorException("Failed to process transaction due to Kafka error");
            }
        }
    }


    @Override
    @Transactional
    public void refillBalance(BalanceOperationDto balanceOperationDto) {
        var account=accountRepository.findById(balanceOperationDto.id());
        var accountBalance=account.get().getBalance();
        if(account.isPresent()){
            account.get().setBalance(accountBalance.add(balanceOperationDto.sum()));
            accountRepository.save(account.get());

            try {
                producer.sendTransactionsRefillBalanceMessage(new Transaction(null,balanceOperationDto.id(),
                        balanceOperationDto.sum(),balanceOperationDto.currency(),LocalDate.now()));
            } catch (JsonProcessingException e) {
                throw new InternalServerErrorException("Failed to process transaction due to Kafka error");
            }
        }
        else{
            throw new InvalidDataException("Account with this id not found","Account not found");
        }
    }
}
