package com.BankingApplicatio.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreationRequest {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
}