package com.BankingApplicatio.Application.exception.Error;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private int status;
    private Instant timestamp;
    private String path;
}