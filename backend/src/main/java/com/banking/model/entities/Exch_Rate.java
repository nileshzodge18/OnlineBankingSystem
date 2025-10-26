package com.banking.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXCH_RATES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exch_Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID_EXCH_RATES",nullable = false)
    private Integer uidExchRate;

    @Column(name = "CCY1",nullable = false)
    private String CCY1;

    @Column(name = "CCY2",nullable = false)
    private String CCY2;

    @Column(name = "CONV_RATE",nullable = false)
    private Double convRate;
}
