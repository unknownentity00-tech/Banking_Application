package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BankingException{
    public AccountNotFoundException(String  message) {
        super("ACCOUNT_NOT_FOUND", message, HttpStatus.NOT_FOUND);
    }
}
