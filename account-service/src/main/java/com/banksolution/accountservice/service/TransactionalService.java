package com.banksolution.accountservice.service;

import com.banksolution.accountservice.dto.transactional.BalanceOperationDto;
import com.banksolution.accountservice.dto.transactional.TransferMoneyDto;

public interface TransactionalService {
    void transferFundsBetweenAccounts(TransferMoneyDto transferMoneyDto);
    void refillBalance(BalanceOperationDto balanceOperationDto);
    void writeOffBalance(BalanceOperationDto balanceOperationDto);
}
