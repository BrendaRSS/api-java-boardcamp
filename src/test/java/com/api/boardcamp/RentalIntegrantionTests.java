package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.boardcamp.dtos.RentalDTO;
import com.api.boardcamp.models.CustomersModel;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.models.RentalModel;
import com.api.boardcamp.repositories.CustomersRepository;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalIntegrantionTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase(){
        rentalRepository.deleteAll();
        customersRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void givenDaysRentedEqualsZero_whenCreatingRental_thenThrowsError(){
        CustomersModel customer = new CustomersModel(null, "name", "12345678910");
        CustomersModel createdCustomer = customersRepository.save(customer);

        GameModel game = new GameModel(null, "name", "https://s2.glbimg.com/4Ek8CnZSuYyyvaNQEPPiX_d-faA=/e.glbimg.com/og/ed/f/original/2017/11/24/gali1.jpg", 5, 3500);
        GameModel createdGame = gameRepository.save(game);

        RentalDTO rentalDTO = new RentalDTO(createdCustomer.getId(), createdGame.getId(), 0);
        HttpEntity<RentalDTO> body = new HttpEntity<>(rentalDTO);

        ResponseEntity<Object> response =  restTemplate.exchange(
            "/rentals",
            HttpMethod.POST,
            body, 
            Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(0, rentalRepository.count());
        
    }
    
}
