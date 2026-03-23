package com.BankingApplicatio.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationRequest {
    private String accountNumber;
    private String accountType;
    private Long customerId;
}

