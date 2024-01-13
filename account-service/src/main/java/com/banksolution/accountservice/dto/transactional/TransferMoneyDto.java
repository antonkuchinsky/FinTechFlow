package com.banksolution.accountservice.dto.transactional;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferMoneyDto(
        UUID senderId,
        UUID recepientId,
        @Pattern(regexp = "USD", message = "The currency can be 'USD'")
        @NotNull
        String currency,
        @NotNull
        @NotEmpty
        BigDecimal money
) {
}
