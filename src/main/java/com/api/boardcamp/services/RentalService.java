package com.api.boardcamp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.boardcamp.models.RentalModel;
import com.api.boardcamp.repositories.RentalRepository;

@Service
public class RentalService {

    final RentalRepository rentalRepository;

    RentalService(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }

    public List<RentalModel> findAll(){
        return rentalRepository.findAll();
    }
    
}
