package com.banking.controller.entities;

import com.banking.model.entities.Account;
import com.banking.service.entities.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/retrieveAccount/{uid_account}")
    public ResponseEntity<Optional<Account>> fetchAccount(@PathVariable Integer uid_account){
        return new ResponseEntity<>(accountService.retrieveAccountDetails(uid_account),HttpStatus.OK);

    }
}
