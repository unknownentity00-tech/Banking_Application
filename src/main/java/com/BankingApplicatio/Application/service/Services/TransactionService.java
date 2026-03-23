package com.BankingApplicatio.Application.service.Services;

import com.BankingApplicatio.Application.dto.TransactionCreationRequest;
import com.BankingApplicatio.Application.dto.TransactionDTO;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {
    @Transactional
    TransactionDTO deposit(TransactionCreationRequest request);

    @Transactional
    TransactionDTO  withdraw(TransactionCreationRequest request);

    @Transactional
    TransactionDTO tranfer(TransactionCreationRequest request);
}
