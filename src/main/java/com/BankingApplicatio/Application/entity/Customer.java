package com.BankingApplicatio.Application.entity;

import com.BankingApplicatio.Application.entity.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customare_Details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;



    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String address;

    @Column(nullable = false, unique = true)
    String phoneNumber;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


}
