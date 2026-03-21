package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;



import org.springframework.http.HttpStatus;

public class TransactionFailedException extends BankingException {
    public TransactionFailedException(String message) {
        super("TRANSACTION_FAILED", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}