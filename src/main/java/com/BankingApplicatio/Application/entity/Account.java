package com.BankingApplicatio.Application.entity;

import com.BankingApplicatio.Application.entity.Enum.AccountStatus;
import com.BankingApplicatio.Application.entity.Enum.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {


        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(nullable = false, unique = true)
        private String accountNumber;

        @Column(nullable = false)
        private BigDecimal balance;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AccountType accountType;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AccountStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDate dateCreated;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = LocalDate.now();
    }
    @Column(nullable = true)
        private LocalDate dateClosed;

        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;
    }

