package com.BankingApplicatio.Application.exception;

import org.springframework.http.HttpStatus;

public class DailyLimitExceededException extends BankingException {
    public DailyLimitExceededException(String  message) {
        super("DAILY_LIMIT_EXCEDDED", message,HttpStatus.TOO_MANY_REQUESTS);
    }
}
