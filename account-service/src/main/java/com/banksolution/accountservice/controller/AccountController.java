package com.banksolution.accountservice.controller;

import com.banksolution.accountservice.dto.AccountDto;
import com.banksolution.accountservice.dto.AccountStatusDto;
import com.banksolution.accountservice.dto.AccountTypeDto;
import com.banksolution.accountservice.dto.transactional.BalanceOperationDto;
import com.banksolution.accountservice.dto.transactional.TransferMoneyDto;
import com.banksolution.accountservice.service.AccountService;
import com.banksolution.accountservice.service.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final TransactionalService transactionalService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable UUID id){
        return accountService.getBalance(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody AccountDto accountDto){
      accountService.createAccount(accountDto);
    }

    @PatchMapping("/change/type")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeAccountType(@RequestBody AccountTypeDto accountTypeDto){
        accountService.changeAccountType(accountTypeDto);
    }

    @PatchMapping("/change/status")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeAccountStatus(@RequestBody AccountStatusDto accountStatusDto){
        accountService.changeAccountStatus(accountStatusDto);
    }

    @PatchMapping("/transaction/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public void transferFundsBetweenAccounts(@RequestBody TransferMoneyDto transferMoneyDto){
     transactionalService.transferFundsBetweenAccounts(transferMoneyDto);
    }

    @PatchMapping("/transaction/refill")
    @ResponseStatus(HttpStatus.CREATED)
    public void refillBalance(@RequestBody BalanceOperationDto balanceOperationDto){
        transactionalService.refillBalance(balanceOperationDto);
    }
}
