package com.banksolution.accountservice.mapper;

import com.banksolution.accountservice.dto.AccountDto;
import com.banksolution.accountservice.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

@Service
public class AccountMapperDto implements Function<AccountDto, Account> {
    @Override
    public Account apply(AccountDto accountDto) {
        return Account.builder()
                .balance(BigDecimal.valueOf(0))
                .firstName(accountDto.firstName())
                .lastName(accountDto.lastName())
                .accountType(accountDto.accountType())
                .currency(accountDto.currency())
                .status("active")
                .openingDate(LocalDate.now())
                .closingDate(LocalDate.now().plusYears(5))
                .build();
    }
}
