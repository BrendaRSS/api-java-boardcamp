package com.api.boardcamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.boardcamp.dtos.RentalDTO;
import com.api.boardcamp.exceptions.RentalDaysException;
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

		RentalDTO rentalDTO = new RentalDTO(1L, 1L, 0);

		RentalDaysException exception = assertThrows(
			RentalDaysException.class, 
			()-> rentalService.save(rentalDTO));

		assertNotNull(exception);
		assertEquals("daysRented must be a number greater than 0", exception.getMessage());
		verify(rentalRepository, times(0)).save(any());
	}
}
