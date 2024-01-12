package com.banksolution.accountservice.dto.transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Transaction(
        UUID senderId,
        UUID recepientId,
        BigDecimal sum,
        String currency,
        LocalDate date
) {
}
