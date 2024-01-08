package com.banksolution.accountservice.account;

import com.banksolution.accountservice.dto.AccountDto;
import com.banksolution.accountservice.dto.AccountStatusDto;
import com.banksolution.accountservice.dto.AccountTypeDto;
import com.banksolution.accountservice.exception.InvalidDataException;
import com.banksolution.accountservice.mapper.AccountMapperDto;
import com.banksolution.accountservice.model.Account;
import com.banksolution.accountservice.repository.AccountRepository;
import com.banksolution.accountservice.service.impl.AccountServiceImpl;
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

class AccountServiceTest {
    @Mock
    private AccountMapperDto accountMapperDto;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        AccountDto accountDto = new AccountDto("John", "Doe", "creditional", "USD");
        Account account = new Account();

        when(accountMapperDto.apply(accountDto)).thenReturn(account);

        accountService.createAccount(accountDto);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
     void testGetBalanceWithValidId() {
        UUID accountId = UUID.randomUUID();
        Account account = new Account();
        account.setBalance(new BigDecimal("100.00"));

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        BigDecimal balance = accountService.getBalance(accountId);

        assertEquals(new BigDecimal("100.00"), balance);
    }

    @Test
     void testGetBalanceWithInvalidId() {
        UUID accountId = UUID.randomUUID();

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            accountService.getBalance(accountId);
        });
    }

    @Test
     void testChangeAccountTypeWithValidId() {
        AccountTypeDto accountTypeDto = new AccountTypeDto(UUID.randomUUID(), "creditional");
        Account account = new Account();

        when(accountRepository.findById(accountTypeDto.accountId())).thenReturn(Optional.of(account));

        accountService.changeAccountType(accountTypeDto);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
     void testChangeAccountTypeWithInvalidId() {
        AccountTypeDto accountTypeDto = new AccountTypeDto(UUID.randomUUID(), "creditional");

        when(accountRepository.findById(accountTypeDto.accountId())).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            accountService.changeAccountType(accountTypeDto);
        });
    }

    @Test
     void testChangeAccountStatusWithValidId() {
        AccountStatusDto accountStatusDto = new AccountStatusDto(UUID.randomUUID(), "active");
        Account account = new Account();

        when(accountRepository.findById(accountStatusDto.id())).thenReturn(Optional.of(account));

        accountService.changeAccountStatus(accountStatusDto);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
     void testChangeAccountStatusWithInvalidId() {
        AccountStatusDto accountStatusDto = new AccountStatusDto(UUID.randomUUID(), "active");

        when(accountRepository.findById(accountStatusDto.id())).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            accountService.changeAccountStatus(accountStatusDto);
        });
    }
}
