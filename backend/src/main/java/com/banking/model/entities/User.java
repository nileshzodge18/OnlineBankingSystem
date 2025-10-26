package com.banking.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID_USERS",unique = true)
    private int uidUsers;

    @Column(name = "FIRSTNAME",nullable = false)
    private String firstName;

    @Column(name = "LASTNAME",nullable = false)
    private String lastName;

    @Column(name = "USERNAME",nullable = false)
    private String username;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NO",nullable = false)
    private String mobile_no;

    @Column(name = "CITY",nullable = false)
    private String city;

    @Column(name = "COUNTRY",nullable = false)
    private String country;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "ADDRESS3")
    private String address3;

    @Column(name = "PIN_CODE",nullable = false)
    private String pinCode;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @Column(name = "ACTIVE", nullable = false)
    private String active;

}
