package com.api.boardcamp.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.RentalDTO;
import com.api.boardcamp.exceptions.CustomersNotFoundException;
import com.api.boardcamp.exceptions.GameNotFoundException;
import com.api.boardcamp.exceptions.GameOutOfStockException;
import com.api.boardcamp.exceptions.RentalAlreadyRefundedException;
import com.api.boardcamp.exceptions.RentalDaysException;
import com.api.boardcamp.exceptions.RentalNotFoundException;
import com.api.boardcamp.models.CustomersModel;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.models.RentalModel;
import com.api.boardcamp.repositories.CustomersRepository;
import com.api.boardcamp.repositories.GameRepository;
import com.api.boardcamp.repositories.RentalRepository;

@Service
public class RentalService {

    final RentalRepository rentalRepository;
    final GameRepository gameRepository;
    final CustomersRepository customersRepository;

    RentalService(RentalRepository rentalRepository, CustomersRepository customersRepository, GameRepository gameRepository){
        this.rentalRepository = rentalRepository;
        this.customersRepository = customersRepository;
        this.gameRepository = gameRepository;
    }

    public List<RentalModel> findAll(){
        return rentalRepository.findAll();
    }
    
    public RentalModel save(RentalDTO body){

        if(body.getDaysRented() == 0){
            throw new RentalDaysException("daysRented must be a number greater than 0");
        }

        CustomersModel customer = customersRepository.findById(body.getCustomerId()).orElseThrow(
            () -> new CustomersNotFoundException("Customer not found by this id")
        );

        GameModel game = gameRepository.findById(body.getGameId()).orElseThrow(
            () -> new GameNotFoundException("Game not found by this id")
        );

        List<RentalModel> rentals = rentalRepository.findByGameId(body.getGameId());

        List<RentalModel> rentalsOpen = rentals.stream().filter(rental -> rental.getReturnDate() == null).collect(Collectors.toList());

        if(rentalsOpen.size() >= game.getStockTotal()){
            throw new GameOutOfStockException("There is no stock of this game at the moment");
        }

        RentalModel rental = new RentalModel(body, customer, game);
        return rentalRepository.save(rental);
    }

    public RentalModel update(Long id){
        RentalModel rental = rentalRepository.findById(id).orElseThrow(
            ()-> new RentalNotFoundException("Rental not found by this id")
        );

        if(rental.getReturnDate() != null){
            throw new RentalAlreadyRefundedException("rent already refunded");
        }

        LocalDate today = LocalDate.now();
        rental.setReturnDate(today);

        int daysDelayed = (int) ChronoUnit.DAYS.between(rental.getRentDate(), today) - rental.getDaysRented();
        int delayFee = Math.max(0, daysDelayed) * rental.getGame().getPricePerDay();

        rental.setDelayFee(delayFee);

        rental = rentalRepository.save(rental);

        return rental;

    }
}
