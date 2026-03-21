package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends BankingException {
    public AccountAlreadyExistsException(String message) {
        super("ACCOUNT_ALREADY_EXISTS", message, HttpStatus.NOT_FOUND);
    }
}
