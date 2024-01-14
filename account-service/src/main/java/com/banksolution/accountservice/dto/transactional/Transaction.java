package com.banksolution.accountservice.dto.transactional;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Transaction(
        UUID senderId,
        UUID recepientId,
        @NotNull
        BigDecimal sum,
        @NotNull
        String currency,
        LocalDate date
) {
}
