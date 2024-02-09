package com.api.boardcamp.exceptions;

public class GameOutOfStockException extends RuntimeException {
    
    public GameOutOfStockException(String message){
        super(message);
    }
}
