package com.BankingApplicatio.Application.controller;

import com.BankingApplicatio.Application.dto.CustomerCreationRequest;
import com.BankingApplicatio.Application.dto.CustomerDTO;
import com.BankingApplicatio.Application.repository.CustomerRepository;
import com.BankingApplicatio.Application.service.Services.CustomerService;
import com.BankingApplicatio.Application.service.serviceImpl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestBody CustomerCreationRequest request) {
        CustomerDTO created = customerService.createCustomer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreationRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }


}
