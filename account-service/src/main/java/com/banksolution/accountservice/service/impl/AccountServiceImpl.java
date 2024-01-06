package com.banksolution.accountservice.service.impl;

import com.banksolution.accountservice.dto.AccountDto;
import com.banksolution.accountservice.dto.AccountStatusDto;
import com.banksolution.accountservice.dto.AccountTypeDto;
import com.banksolution.accountservice.exception.InvalidDataException;
import com.banksolution.accountservice.mapper.AccountMapperDto;
import com.banksolution.accountservice.repository.AccountRepository;
import com.banksolution.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapperDto accountMapperDto;
    private final AccountRepository accountRepository;

    @Override
    public void createAccount(AccountDto accountDto) {
    accountRepository.save(accountMapperDto.apply(accountDto));
    }

    @Override
    public BigDecimal getBalance(UUID id) {
        var account=accountRepository.findById(id);
        if(account.isPresent()){
            return account.get().getBalance();
        }
        else{
            throw new InvalidDataException("Account with this id not found","Account not found");
        }
    }

    @Override
    public void changeAccountType(AccountTypeDto accountTypeDto) {
        var account=accountRepository.findById(accountTypeDto.accountId());
        if(account.isPresent()){
            account.get().setAccountType(accountTypeDto.accountType());
            accountRepository.save(account.get());
        }
        else{
            throw new InvalidDataException("Account with this id not found","Account not found");
        }
    }

    @Override
    public void changeAccountStatus(AccountStatusDto accountStatusDto) {
        var account=accountRepository.findById(accountStatusDto.id());
        if(account.isPresent()){
            account.get().setStatus(accountStatusDto.status());
            accountRepository.save(account.get());
        }
        else{
            throw new InvalidDataException("Account with this id not found","Account not found");
        }
    }
}
