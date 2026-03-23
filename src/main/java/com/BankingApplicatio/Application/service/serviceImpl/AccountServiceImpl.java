package com.BankingApplicatio.Application.service.serviceImpl;

import com.BankingApplicatio.Application.dto.AccountCreationRequest;
import com.BankingApplicatio.Application.dto.AccountDTO;
import com.BankingApplicatio.Application.entity.Account;
import com.BankingApplicatio.Application.entity.Customer;
import com.BankingApplicatio.Application.entity.Enum.AccountStatus;
import com.BankingApplicatio.Application.entity.Enum.AccountType;
import com.BankingApplicatio.Application.exception.AccountNotFoundException;
import com.BankingApplicatio.Application.exception.UserNotFoundException;
import com.BankingApplicatio.Application.repository.AccountRepository;
import com.BankingApplicatio.Application.repository.CustomerRepository;
import com.BankingApplicatio.Application.service.Services.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


     private final AccountRepository accountRepository;
     private  final CustomerRepository customerRepository;
     private ModelMapper modelMapper;

    @Override
    public AccountDTO createAccount(AccountCreationRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Customer not found with id: " + request.getCustomerId()
                ));

        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(AccountType.valueOf(
                request.getAccountType().toUpperCase()));
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCustomer(customer);

        Account saved = accountRepository.save(account);
        return modelMapper.map(saved, AccountDTO.class);
    }
    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public AccountDTO updateAccount(Long id, AccountCreationRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + id
                ));
        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(AccountType.valueOf(request.getAccountType().toUpperCase()));
        Account updatedAccount = accountRepository.save(account);
        return modelMapper.map(updatedAccount, AccountDTO.class);
    }


    @Override
    public AccountDTO deleteAccount(long id){
         Account account = accountRepository.findById(id)
                 .orElseThrow(() -> new AccountNotFoundException(
                         "Account not found with id: " + id
                 ));
         accountRepository.delete(account);
         return modelMapper.map(account, AccountDTO.class);
    }
}
