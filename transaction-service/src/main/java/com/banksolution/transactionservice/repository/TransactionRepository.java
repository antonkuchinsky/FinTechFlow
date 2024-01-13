package com.banksolution.transactionservice.repository;

import com.banksolution.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE MONTH(t.date) = MONTH(CURRENT_DATE) AND YEAR(t.date) = YEAR(CURRENT_DATE) AND (t.recepientId = :id)")
    Optional<BigDecimal> getTotalTransactionsForMonthForRecepient(@Param("id") UUID id);

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE EXTRACT(YEAR FROM t.date) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(WEEK FROM t.date) = EXTRACT(WEEK FROM CURRENT_DATE) AND (t.recepientId = :id)")
    Optional<BigDecimal> getTotalTransactionsForWeekForRecepient(@Param("id") UUID id);

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE t.date = CURRENT_DATE AND (t.recepientId = :id)")
    Optional<BigDecimal> getTotalTransactionsForDayForRecepient(@Param("id") UUID id);

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE MONTH(t.date) = MONTH(CURRENT_DATE) AND YEAR(t.date) = YEAR(CURRENT_DATE) AND (t.senderId = :id)")
    Optional<BigDecimal> getTotalTransactionsForMonthForSender(@Param("id") UUID id);

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE EXTRACT(YEAR FROM t.date) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(WEEK FROM t.date) = EXTRACT(WEEK FROM CURRENT_DATE) AND (t.senderId = :id)")
    Optional<BigDecimal> getTotalTransactionsForWeekForSender(@Param("id") UUID id);

    @Query("SELECT COALESCE(SUM(t.sum), 0) FROM Transaction t WHERE t.date = CURRENT_DATE AND (t.senderId = :id)")
    Optional<BigDecimal> getTotalTransactionsForDayForSender(@Param("id") UUID id);
}

