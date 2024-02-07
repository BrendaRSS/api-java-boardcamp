package com.api.boardcamp.exceptions;

public class GamePriceNotNullException extends RuntimeException {
    
    public GamePriceNotNullException(String message){
        super(message);
    }
}
