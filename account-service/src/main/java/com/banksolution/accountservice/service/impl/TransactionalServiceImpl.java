package com.banksolution.accountservice.service.impl;

import com.banksolution.accountservice.dto.transactional.BalanceOperationDto;
import com.banksolution.accountservice.dto.transactional.TransferMoneyDto;
import com.banksolution.accountservice.exception.InsufficientFundsException;
import com.banksolution.accountservice.exception.InvalidDataException;
import com.banksolution.accountservice.repository.AccountRepository;
import com.banksolution.accountservice.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionalServiceImpl implements TransactionalService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void transferFundsBetweenAccounts(TransferMoneyDto transferMoneyDto) {
     var sender=accountRepository.findById(transferMoneyDto.senderId());
     var senderBalance=sender.get().getBalance();

     var recepient=accountRepository.findById(transferMoneyDto.recepientId());
     var recepientBalance=recepient.get().getBalance();

     if(senderBalance.subtract(transferMoneyDto.money()).compareTo(BigDecimal.ZERO) < 0){
         throw new InsufficientFundsException("There are insufficient funds in the sender's account","Insufficient funds");
     }

     else{
         sender.get().setBalance(senderBalance.subtract(transferMoneyDto.money()));
         recepient.get().setBalance(recepientBalance.add(transferMoneyDto.money()));
         accountRepository.save(sender.get());
         accountRepository.save(recepient.get());
     }
    }

    @Override
    public void refillBalance(BalanceOperationDto balanceOperationDto) {
        var account=accountRepository.findById(balanceOperationDto.id());
        var accountBalance=account.get().getBalance();
        if(account.isPresent()){
            account.get().setBalance(accountBalance.add(balanceOperationDto.sum()));
            accountRepository.save(account.get());
        }
        else{
            throw new InvalidDataException("Account with this id not found","Account not found");
        }
    }
}
