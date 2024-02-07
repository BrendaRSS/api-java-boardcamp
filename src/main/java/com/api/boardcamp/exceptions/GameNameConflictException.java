package com.api.boardcamp.exceptions;

public class GameNameConflictException extends RuntimeException {
    
    public GameNameConflictException(String message){
        super(message);
    }
}
