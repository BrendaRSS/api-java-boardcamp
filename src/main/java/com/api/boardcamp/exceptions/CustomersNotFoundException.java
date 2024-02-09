package com.api.boardcamp.exceptions;

public class CustomersNotFoundException extends RuntimeException{
    public CustomersNotFoundException(String message){
        super(message);
    }
}
