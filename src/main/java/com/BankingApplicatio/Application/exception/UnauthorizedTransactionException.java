package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;


import org.springframework.http.HttpStatus;

public class UnauthorizedTransactionException extends BankingException {
    public UnauthorizedTransactionException(String message) {
        super("UNAUTHORIZED_TRANSACTION", message, HttpStatus.FORBIDDEN);
    }
}
