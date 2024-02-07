package com.api.boardcamp.exceptions;

public class GameNameNotNullException extends RuntimeException {
    
   public GameNameNotNullException(String message){
        super(message);
    }
}
