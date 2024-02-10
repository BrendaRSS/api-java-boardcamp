package com.api.boardcamp.exceptions;

public class RentalNotFoundException extends RuntimeException{
    
    public RentalNotFoundException(String message){
        super(message);
    }
}
