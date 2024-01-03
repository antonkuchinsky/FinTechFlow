package com.banksolution.accountservice.dto.transactional;

import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferMoneyDto(
        UUID senderId,
        UUID recepientId,
        @NotNull
        @NotEmpty
        BigDecimal money
) {
}
