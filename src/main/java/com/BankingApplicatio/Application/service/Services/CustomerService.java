package com.BankingApplicatio.Application.service.Services;

import com.BankingApplicatio.Application.dto.CustomerCreationRequest;
import com.BankingApplicatio.Application.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerCreationRequest Request);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO   updateCustomer(Long id, CustomerCreationRequest request);

    CustomerDTO deleteCustomer(Long id);
}
