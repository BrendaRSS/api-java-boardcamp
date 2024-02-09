package com.api.boardcamp.exceptions;

public class CustomersNameNotNullException extends RuntimeException{
    public CustomersNameNotNullException(String message){
        super(message);
    }
}
