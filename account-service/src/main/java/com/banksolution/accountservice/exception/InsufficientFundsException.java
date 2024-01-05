package com.banksolution.accountservice.exception;

public class InsufficientFundsException extends RuntimeException{
    private final String title;

    public InsufficientFundsException(String message, String title) {
        super(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
