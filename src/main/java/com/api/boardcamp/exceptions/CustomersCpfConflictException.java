package com.api.boardcamp.exceptions;

public class CustomersCpfConflictException extends RuntimeException{
    public CustomersCpfConflictException(String message){
        super(message);
    }
}
