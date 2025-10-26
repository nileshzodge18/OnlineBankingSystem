package com.banking.exceptions;

public class NullPointerException extends RuntimeException{

    private final String message;
    public NullPointerException(String message){
        super(message);
        this.message = message;
    }
}
