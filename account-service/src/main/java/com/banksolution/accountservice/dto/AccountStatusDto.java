package com.banksolution.accountservice.dto;

import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record AccountStatusDto(
        UUID id,
        @Pattern(regexp = "blocked|active", message = "The status can be 'blocked' or 'active'")
        String status
) {
}
