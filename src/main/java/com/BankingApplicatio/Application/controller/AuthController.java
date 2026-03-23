package com.BankingApplicatio.Application.controller;


import com.BankingApplicatio.Application.dto.CustomerCreationRequest;
import com.BankingApplicatio.Application.dto.CustomerDTO;
import com.BankingApplicatio.Application.dto.LoginRequest;
import com.BankingApplicatio.Application.entity.Customer;
import com.BankingApplicatio.Application.entity.Enum.Role;
import com.BankingApplicatio.Application.exception.UserNotFoundException;
import com.BankingApplicatio.Application.repository.CustomerRepository;
import com.BankingApplicatio.Application.security.JwtService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
   private final ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> register(
            @RequestBody CustomerCreationRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setPassword(
                passwordEncoder.encode(request.getPassword()));
        customer.setRole(Role.CUSTOMER);

        Customer saved = customerRepository.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(saved, CustomerDTO.class));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        Customer customer = customerRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found: " + request.getEmail()
                ));


        String token = jwtService.generateToken(customer);


        return ResponseEntity.ok(token);
    }

}
