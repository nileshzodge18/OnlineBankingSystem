package com.banking.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TXN_ERROR")
public class TxnError {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "UID_TXN_ERROR")
    private Integer uidTxnError;
}
