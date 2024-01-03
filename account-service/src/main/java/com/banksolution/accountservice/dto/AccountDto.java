package com.banksolution.accountservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AccountDto(
        @NotEmpty(message = "First name can't be empty")
        @NotNull(message = "First name is not null")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я ]+$", message = "First name must contain only letters and spaces")
        @Length(min = 2, max = 50, message = "The length of the first name must be from 2 to 50 characters")
        String firstName,

        @NotEmpty(message = "Last name can't be empty")
        @NotNull(message = "Last name is not null")
        @Pattern(regexp = "^[a-zA-Zа-яА-Я ]+$", message = "Last name must contain only letters and spaces")
        @Length(min = 2, max = 50, message = "The length of the last name must be from 2 to 50 characters")
        String lastName,

        @Pattern(regexp = "creditional|physical", message = "The account type can be 'creditional' or 'physical'")
        String accountType,

        @Pattern(regexp = "USD|BYN", message = "The currency can be 'USD' or 'BYN'")
        String currency
) {
}
