package com.api.boardcamp.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long gameId;

    private LocalDate rentDate;

    @NotNull
    private int daysRented;

    private LocalDate returnDate;

    private int originalPrice; 
    
    private int delayFee;
}
