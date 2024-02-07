package com.api.boardcamp.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank
    private String name;

    @NotBlank
    @URL(message = "The type must be a URL")
    private String image;

    // @Positive(message = "Stock total must be greater than zero")
    private int stockTotal;

    // @Positive(message = "Price per day must be greater than zero")
    private int pricePerDay;
}
