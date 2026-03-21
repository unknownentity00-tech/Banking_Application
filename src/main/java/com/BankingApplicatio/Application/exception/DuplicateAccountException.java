package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class DuplicateAccountException extends BankingException {
    public DuplicateAccountException(String  message) {
        super("ACCOUNT_EXISTS", message, HttpStatus.CONFLICT);
    }
}
