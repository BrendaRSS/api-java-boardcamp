package com.api.boardcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Game
    @ExceptionHandler({GameNameConflictException.class})
    public ResponseEntity<Object> handleGameNameConflict(GameNameConflictException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({GameNameNotNullException.class})
    public ResponseEntity<Object> handleGameNameNotNull(GameNameNotNullException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({GamePriceNotNullException.class})
    public ResponseEntity<Object> handleGamePriceNotNull(GamePriceNotNullException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({GameStockNotNullException.class})
    public ResponseEntity<Object> handleGameStockNotNull(GameStockNotNullException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    // Customer

    @ExceptionHandler({CustomersIllegalArgumentException.class})
    public ResponseEntity<Object> handleCustomersIllegalArguments(CustomersIllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({CustomersNameNotNullException.class})
    public ResponseEntity<Object> handleCustomersNameNotNull(CustomersNameNotNullException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({CustomersCpfConflictException.class})
    public ResponseEntity<Object> handleCustomersCpfConflict(CustomersCpfConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({CustomersNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFound(CustomersNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // Rental

    @ExceptionHandler({RentalDaysException.class})
    public ResponseEntity<Object> handleRentalDays(RentalDaysException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<Object> handleGameNotFound(GameNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({GameOutOfStockException.class})
    public ResponseEntity<Object> handleGameOutOfStock(GameOutOfStockException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler({RentalNotFoundException.class})
    public ResponseEntity<Object> handleRentalNotFound(RentalNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({RentalAlreadyRefundedException.class})
    public ResponseEntity<Object> handleRentalAlreadyRefunded(RentalAlreadyRefundedException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
