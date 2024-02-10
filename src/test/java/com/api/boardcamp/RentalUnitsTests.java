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
import com.api.boardcamp.exceptions.CustomersNotFoundException;
import com.api.boardcamp.exceptions.GameNotFoundException;
import com.api.boardcamp.exceptions.RentalDaysException;
import com.api.boardcamp.models.CustomersModel;
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

	@Test
	void givenWrongCustomerId_whenCreatingRental_thenThrowsError(){

		RentalDTO rentalDTO = new RentalDTO(1L, 1L, 3);

		doReturn(Optional.empty()).when(customersRepository).findById(any());

		CustomersNotFoundException exception = assertThrows(
			CustomersNotFoundException.class, 
			()-> rentalService.save(rentalDTO));

		assertNotNull(exception);
		assertEquals("Customer not found by this id", exception.getMessage());
		verify(rentalRepository, times(0)).save(any());
		verify(customersRepository, times(1)).findById(any());
	}

	@Test
	void givenWrongGameId_whenCreatingRental_thenTrhowsError(){
		RentalDTO rentalDTO = new RentalDTO(1L, 1L, 3);
		CustomersModel customer = new CustomersModel(1L, "name", "12345678910");

		doReturn(Optional.of(customer)).when(customersRepository).findById(any());
		doReturn(Optional.empty()).when(gameRepository).findById(any());

		GameNotFoundException exception = assertThrows(
			GameNotFoundException.class, 
			()-> rentalService.save(rentalDTO));

		assertNotNull(exception);
		assertEquals("Game not found by this id", exception.getMessage());
		verify(rentalRepository, times(0)).save(any());
		verify(customersRepository, times(1)).findById(any());
		verify(gameRepository, times(1)).findById(any());

	}
}
