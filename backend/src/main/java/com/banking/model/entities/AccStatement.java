package com.banking.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACC_STATEMENT")
public class AccStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "UID_ACC_STATEMENT")
    private Integer uidAccStatement;

    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "TXN_DATE")
    private java.time.LocalDateTime txnDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "TXN_TYPE")
    private String txnType;


}
