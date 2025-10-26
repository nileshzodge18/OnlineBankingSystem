package com.banking.model.entities;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID_ACCOUNTS",nullable = false,unique = true)
    private Integer uidAccounts;

    @Column(name = "ACC_NO",nullable = false,unique = true)
    private String accNo;

    @Column(name = "UID_USERS",nullable = false)
    private Integer uidUsers;

    @Column(name = "ACC_TYPE",nullable = false)
    private String accType;

    @Column(name = "ACC_BALANCE",nullable = false)
    private Double accBalance;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "ACTIVE", nullable = false)
    private String active;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

}
