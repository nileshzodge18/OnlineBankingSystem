package com.banking.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BANKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID_BANKS")
    private Integer uidBanks;

    @Column(name = "LOCAL_BANK")
    private Boolean localBank;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_IFSC")
    private String bankIFSCCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ACTIVE")
    private String active;

}


