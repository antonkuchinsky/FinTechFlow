package com.banksolution.accountservice.transaction;

import com.banksolution.accountservice.dto.transactional.BalanceOperationDto;
import com.banksolution.accountservice.dto.transactional.TransferMoneyDto;
import com.banksolution.accountservice.exception.InsufficientFundsException;
import com.banksolution.accountservice.exception.InvalidDataException;
import com.banksolution.accountservice.model.Account;
import com.banksolution.accountservice.repository.AccountRepository;
import com.banksolution.accountservice.service.impl.TransactionalServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionalServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionalServiceImpl transactionalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testTransferFundsBetweenAccounts() {
        Account sender = new Account();
        sender.setId(UUID.randomUUID());
        sender.setBalance(new BigDecimal("100.00"));

        Account recipient = new Account();
        recipient.setId(UUID.randomUUID());
        recipient.setBalance(new BigDecimal("50.00"));

        TransferMoneyDto transferMoneyDto = new TransferMoneyDto(sender.getId(), recipient.getId(), new BigDecimal("25.00"));

        when(accountRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountRepository.findById(recipient.getId())).thenReturn(Optional.of(recipient));

        transactionalService.transferFundsBetweenAccounts(transferMoneyDto);

        assertEquals(new BigDecimal("75.00"), sender.getBalance());
        assertEquals(new BigDecimal("75.00"), recipient.getBalance());

        verify(accountRepository, times(1)).save(sender);
        verify(accountRepository, times(1)).save(recipient);
    }

    @Test
    public void testRefillBalance() {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setBalance(new BigDecimal("100.00"));

        BalanceOperationDto balanceOperationDto = new BalanceOperationDto(account.getId(), new BigDecimal("50.00"));

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        transactionalService.refillBalance(balanceOperationDto);

        assertEquals(new BigDecimal("150.00"), account.getBalance());

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    @Transactional
    public void testTransferFundsBetweenAccountsWithInsufficientFunds() {
        Account sender = new Account();
        sender.setId(UUID.randomUUID());
        sender.setBalance(new BigDecimal("100.00"));

        Account recipient = new Account();
        recipient.setId(UUID.randomUUID());
        recipient.setBalance(new BigDecimal("50.00"));

        TransferMoneyDto transferMoneyDto = new TransferMoneyDto(sender.getId(), recipient.getId(), new BigDecimal("150.00"));

        when(accountRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountRepository.findById(recipient.getId())).thenReturn(Optional.of(recipient));

        assertThrows(InsufficientFundsException.class, () -> {
            transactionalService.transferFundsBetweenAccounts(transferMoneyDto);
        });

        assertEquals(new BigDecimal("100.00"), sender.getBalance());
        assertEquals(new BigDecimal("50.00"), recipient.getBalance());

        verify(accountRepository, never()).save(any());
    }
}

