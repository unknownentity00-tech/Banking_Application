package com.BankingApplicatio.Application.service.serviceImpl;

import com.BankingApplicatio.Application.dto.CustomerCreationRequest;
import com.BankingApplicatio.Application.dto.CustomerDTO;
import com.BankingApplicatio.Application.entity.Customer;
import com.BankingApplicatio.Application.entity.Enum.Role;
import com.BankingApplicatio.Application.exception.UserNotFoundException;
import com.BankingApplicatio.Application.repository.CustomerRepository;
import com.BankingApplicatio.Application.service.Services.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

    @Override
    public CustomerDTO createCustomer(CustomerCreationRequest Request) {
        Customer customer = modelMapper.map(Request, Customer.class);
        customer.setRole(Role.CUSTOMER);
        Customer saved = customerRepository.save(customer);
        return modelMapper.map(saved, CustomerDTO.class);
    }

   @Override
   public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
   }
  @Override
  public List<CustomerDTO> getAllCustomers(){
       return customerRepository
                .findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());


   }
   @Override
   public CustomerDTO   updateCustomer(Long id, CustomerCreationRequest request){
       Customer customer = customerRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException(
                       "Customer not found with id: " + id
               ));
       customer.setName(request.getName());
       customer.setEmail(request.getEmail());
       customer.setAddress(request.getAddress());
       customer.setPhoneNumber(request.getPhoneNumber());
       return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
    }
   @Override
   public CustomerDTO deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Customer not found with id: " + id
                ));

        customerRepository.delete(customer);
   return modelMapper.map(customer, CustomerDTO.class);

    }

}
