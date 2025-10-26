package com.banking.exceptions;

public class AccountNotFound extends RuntimeException{

    private final String message;
    public AccountNotFound(String message){
        super(message);
        this.message = message;
    }

}
