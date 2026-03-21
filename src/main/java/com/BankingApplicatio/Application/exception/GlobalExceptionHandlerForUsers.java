package com.BankingApplicatio.Application.exception;

import com.BankingApplicatio.Application.exception.Error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandlerForUsers{

    @ExceptionHandler(BankingException.class)
    public ResponseEntity<ErrorResponse> handleBankingException(
            BankingException ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        error.setStatus(ex.getStatus().value());
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatus());
    }
}

