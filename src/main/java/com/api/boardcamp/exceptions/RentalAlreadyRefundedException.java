package com.api.boardcamp.exceptions;

public class RentalAlreadyRefundedException extends RuntimeException {
    
    public RentalAlreadyRefundedException(String message){
        super(message);
    }
}
