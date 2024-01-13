package com.banksolution.transactionservice;

import com.banksolution.transactionservice.exception.InvalidDataException;
import com.banksolution.transactionservice.repository.TransactionRepository;
import com.banksolution.transactionservice.service.impl.TransactionRecepientServiceImpl;
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

class TransactionRecepientServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionRecepientServiceImpl transactionRecepientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionRecepientService = new TransactionRecepientServiceImpl(transactionRepository);
    }

    @Test
    void getTransactionsForMonth_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("100.00");

        when(transactionRepository.getTotalTransactionsForMonthForRecepient(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionRecepientService.getTransactionsForMonth(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForWeek_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("500.00");

        when(transactionRepository.getTotalTransactionsForWeekForRecepient(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionRecepientService.getTransactionsForWeek(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForDay_PositiveTest(){
        UUID id = UUID.randomUUID();
        BigDecimal expectedValue = new BigDecimal("50.00");

        when(transactionRepository.getTotalTransactionsForDayForRecepient(id)).thenReturn(Optional.of(expectedValue));

        BigDecimal actualValue = transactionRecepientService.getTransactionsForDay(id);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTransactionsForMonth_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForMonthForRecepient(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionRecepientService.getTransactionsForMonth(nonExistentId);
        });
    }

    @Test
    void getTransactionsForWeek_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForWeekForRecepient(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionRecepientService.getTransactionsForWeek(nonExistentId);
        });
    }

    @Test
    void getTransactionsForDay_NegativeTest(){
        UUID nonExistentId = UUID.randomUUID();

        when(transactionRepository.getTotalTransactionsForDayForRecepient(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class, () -> {
            transactionRecepientService.getTransactionsForDay(nonExistentId);
        });
    }
}

