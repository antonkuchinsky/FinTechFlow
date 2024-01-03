package com.banksolution.accountservice.dto;

import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record AccountTypeDto(
        UUID accountId,

        @Pattern(regexp = "creditional|physical", message = "The account type can be 'creditional' or 'physical'")
        String accountType
) {
}
