package com.api.boardcamp.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {

    @NotNull(message = "customerId cannot be null")
    private Long customerId;

    @NotNull(message = "gameId cannot be null")
    private Long gameId;

    @NotNull
    @Positive(message = "daysRented must be a number greater than 0")
    private int daysRented;
}
