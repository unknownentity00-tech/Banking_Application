package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BankingException {

    public UserNotFoundException(String message) {
        super("USER_NOT_FOUND", message, HttpStatus.NOT_FOUND);
    }
}