package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;


import org.springframework.http.HttpStatus;

public class InvalidAmountException extends BankingException {
    public InvalidAmountException(String message) {
        super("INVALID_AMOUNT", message, HttpStatus.BAD_REQUEST);
    }
}
