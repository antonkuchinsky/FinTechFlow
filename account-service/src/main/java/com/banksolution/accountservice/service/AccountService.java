package com.banksolution.accountservice.service;

import com.banksolution.accountservice.dto.AccountDto;
import com.banksolution.accountservice.dto.AccountStatusDto;
import com.banksolution.accountservice.dto.AccountTypeDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    void createAccount(AccountDto accountDto);
    BigDecimal getBalance(UUID id);
    void changeAccountType(AccountTypeDto accountTypeDto);
    void changeAccountStatus(AccountStatusDto accountStatusDto);
}
