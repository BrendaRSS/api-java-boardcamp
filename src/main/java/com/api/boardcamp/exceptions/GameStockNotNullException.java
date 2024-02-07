package com.api.boardcamp.exceptions;

public class GameStockNotNullException extends RuntimeException {
    
    public GameStockNotNullException(String message){
        super(message);
    }
}
