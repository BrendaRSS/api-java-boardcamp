package com.api.boardcamp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.boardcamp.dtos.CustomersDTO;
import com.api.boardcamp.models.CustomersModel;
import com.api.boardcamp.services.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    final CustomersService customersService;

    CustomersController(CustomersService customersService){
        this.customersService = customersService;
    }

    @PostMapping
    public ResponseEntity<CustomersModel> postCustomer(@RequestBody @Valid CustomersDTO body){
        CustomersModel customer = customersService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomersModel> getCustomerId(@PathVariable Long id){
        CustomersModel customer = customersService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomersModel>> getAll(){
        List<CustomersModel> customers = customersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    
}
