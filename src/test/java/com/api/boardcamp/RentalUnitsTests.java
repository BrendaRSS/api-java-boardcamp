package com.api.boardcamp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.repositories.CustomersRepository;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.RentalRepository;
import com.api.boardcamp.services.RentalService;

@SpringBootTest
class RentalUnitsTests {

	@InjectMocks
	private RentalService rentalService;

	@Mock
	private RentalRepository rentalRepository;

	@Mock
	private CustomersRepository customersRepository;

	@Mock
	private GameRepository gameRepository;

	@Test
	void givenDaysRentedEqualsZero_whenCreatingRental_thenThrowsError(){

	}
}
