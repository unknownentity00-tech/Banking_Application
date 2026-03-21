package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;



public class InsufficientBalanceException extends BankingException {
    public InsufficientBalanceException(String message) {
        super("INSUFFICIENT_BALANCE", message, HttpStatus.BAD_REQUEST);
    }
}
