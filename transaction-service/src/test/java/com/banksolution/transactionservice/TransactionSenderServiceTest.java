package com.banksolution.transactionservice;

import com.banksolution.transactionservice.exception.InvalidDataException;
import com.banksolution.transactionservice.repository.TransactionRepository;
import com.banksolution.transactionservice.service.impl.TransactionSenderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TransactionSenderServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    private TransactionSenderServiceImpl transactionSenderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionSenderService = new TransactionSenderServiceImpl(transactionRepository);
    }

    @Test
    void getTransactionsForMonth_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("100.00");

        when(transactionRepository.getTotalTransactionsForMonthForSender(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionSenderService.getTransactionsForMonth(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForWeek_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("500.00");

        when(transactionRepository.getTotalTransactionsForWeekForSender(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionSenderService.getTransactionsForWeek(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForDay_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("50.00");

        when(transactionRepository.getTotalTransactionsForDayForSender(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionSenderService.getTransactionsForDay(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForMonth_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForMonthForSender(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionSenderService.getTransactionsForMonth(nonExistentId);
        });
    }

    @Test
    void getTransactionsForWeek_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForWeekForSender(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionSenderService.getTransactionsForWeek(nonExistentId);
        });
    }

    @Test
    void getTransactionsForDay_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForDayForSender(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionSenderService.getTransactionsForDay(nonExistentId);
        });
    }
}
