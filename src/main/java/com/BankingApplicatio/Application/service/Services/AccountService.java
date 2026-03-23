package com.BankingApplicatio.Application.service.Services;

import com.BankingApplicatio.Application.dto.AccountCreationRequest;
import com.BankingApplicatio.Application.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountCreationRequest request);

    AccountDTO getAccountById(Long id);










    List<AccountDTO> getAllAccounts();

    AccountDTO updateAccount(Long id, AccountCreationRequest request);

    AccountDTO deleteAccount(long id);
}
