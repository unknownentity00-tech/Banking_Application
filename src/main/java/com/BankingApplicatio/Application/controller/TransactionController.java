package com.BankingApplicatio.Application.controller;

import com.BankingApplicatio.Application.dto.TransactionCreationRequest;
import com.BankingApplicatio.Application.dto.TransactionDTO;
import com.BankingApplicatio.Application.service.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(
            @RequestBody TransactionCreationRequest request){
            TransactionDTO tranfer = transactionService.deposit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tranfer);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(
            @RequestBody TransactionCreationRequest request){
            TransactionDTO tranfer = transactionService.withdraw(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(tranfer);
            }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> tranfer(
            @RequestBody TransactionCreationRequest request){
            TransactionDTO tranfer = transactionService.tranfer(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(tranfer);
            }

}
