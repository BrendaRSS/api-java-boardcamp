package com.api.boardcamp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.boardcamp.models.RentalModel;
import com.api.boardcamp.services.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    final RentalService rentalService;

    RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalModel>> getAllRentals(){
        List<RentalModel> rentals = rentalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }
    
}
