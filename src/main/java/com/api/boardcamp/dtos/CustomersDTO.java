package com.api.boardcamp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomersDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    
    @NotBlank(message = "CPF cannot be empty")
    @Size(min = 11, max = 11, message = "CPF must have exactly 11 characters")
    private String cpf;
}
