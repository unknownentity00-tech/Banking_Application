package com.BankingApplicatio.Application.service.serviceImpl;
import com.BankingApplicatio.Application.entity.Enum.TransactionStatus;
import com.BankingApplicatio.Application.entity.Enum.TransactionType;
import org.springframework.transaction.annotation.Transactional;
import com.BankingApplicatio.Application.dto.TransactionCreationRequest;
import com.BankingApplicatio.Application.dto.TransactionDTO;
import com.BankingApplicatio.Application.entity.Account;
import com.BankingApplicatio.Application.entity.Transaction;
import com.BankingApplicatio.Application.exception.AccountNotFoundException;
import com.BankingApplicatio.Application.exception.InsufficientBalanceException;
import com.BankingApplicatio.Application.repository.AccountRepository;
import com.BankingApplicatio.Application.repository.TransactionRepository;
import com.BankingApplicatio.Application.service.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl  implements TransactionService {


    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public TransactionDTO deposit(TransactionCreationRequest request) {
        Account account = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + request.getSourceAccountId()
                ));

        account.setBalance(account.getBalance().add(request.getAmount()));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.CREDIT);
        transaction.setSourceAccount(account);
        transaction.setBalanceAfterTransaction(account.getBalance());
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setDescription("Deposit");

        return modelMapper.map(transactionRepository.save(transaction), TransactionDTO.class);
    }

    @Transactional
    @Override
    public TransactionDTO  withdraw(TransactionCreationRequest request) {
        Account account = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + request.getSourceAccountId()
                ));
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(request.getAmount()));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setSourceAccount(account);
        transaction.setBalanceAfterTransaction(account.getBalance());
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setDescription("Withdrawal");

        return modelMapper.map(transactionRepository.save(transaction), TransactionDTO.class);
   }

    @Transactional
    @Override
    public TransactionDTO tranfer(TransactionCreationRequest request){
        Account sourceAccount = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + request.getSourceAccountId()
                ));
        Account destinationAccount = accountRepository.findById(request.getDestinationAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + request.getDestinationAccountId()
                ));
        if (sourceAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(request.getAmount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setBalanceAfterTransaction(sourceAccount.getBalance());
       transaction.setStatus(TransactionStatus.SUCCESS);
       transaction.setDescription("Transfer to account: " +
               destinationAccount.getAccountNumber());
        return modelMapper.map(transactionRepository.save(transaction), TransactionDTO.class);
   }





}
