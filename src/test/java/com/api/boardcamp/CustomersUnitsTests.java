package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.dtos.CustomersDTO;
import com.api.boardcamp.exceptions.CustomersCpfConflictException;
import com.api.boardcamp.repositories.CustomersRepository;
import com.api.boardcamp.services.CustomersService;

@SpringBootTest
public class CustomersUnitsTests {

    @InjectMocks
    private CustomersService customersService;

    @Mock
    private CustomersRepository customersRepository;

    @Test
    void givenRepeatedCpf_whenCreatingCustomer_thenThrowsError(){
        CustomersDTO customerDTO = new CustomersDTO("name", "12345678910");
        
        doReturn(true).when(customersRepository).existsByCpf(any());

        CustomersCpfConflictException exception = assertThrows(
            CustomersCpfConflictException.class, 
            ()-> customersService.save(customerDTO));

        assertNotNull(exception);
        assertEquals("This cpf already exists!", exception.getMessage());
        verify(customersRepository, times(0)).save(any());
        verify(customersRepository, times(1)).existsByCpf(any());
    }
    
}
