package com.api.boardcamp.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDTO {

    @NotBlank
    private String name;

    @NotBlank
    @URL(message = "The type must be a URL")
    private String image;

    @NotBlank
    private int stockTotal;

    @NotBlank
    private int pricePerDay;
}
