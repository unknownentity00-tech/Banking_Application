package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class InvalidAccountTypeException extends BankingException{
    public InvalidAccountTypeException(String errorCode, String message, HttpStatus status) {
        super("INVALID_ACCOUNT_TYPE", message, HttpStatus.BAD_REQUEST);
    }
}
