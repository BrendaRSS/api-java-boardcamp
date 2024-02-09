package com.api.boardcamp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.CustomersDTO;
import com.api.boardcamp.exceptions.CustomersIllegalArgumentException;
import com.api.boardcamp.exceptions.CustomersCpfConflictException;
import com.api.boardcamp.exceptions.CustomersNameNotNullException;
import com.api.boardcamp.exceptions.CustomersNotFoundException;
import com.api.boardcamp.models.CustomersModel;
import com.api.boardcamp.repositories.CustomersRepository;

@Service
public class CustomersService {

    final CustomersRepository customersRepository;

    CustomersService(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    public CustomersModel save(CustomersDTO body){

        if(body.getCpf().length() != 11){
           throw new CustomersIllegalArgumentException("CPF must have exactly 11 characters.");
        }

        if(body.getName().length() == 0){
            throw new CustomersNameNotNullException("Name cannot be empty.");
        }
        
        if(customersRepository.existsByCpf(body.getCpf())){
            throw new CustomersCpfConflictException("This cpf already exists!");
        }

        CustomersModel customer = new CustomersModel(body);

        return customersRepository.save(customer);
    }

    public CustomersModel findById(Long id){
       return customersRepository.findById(id).orElseThrow(
        () -> new CustomersNotFoundException("Customer not found by this id"));
    }

    public List<CustomersModel> findAll(){
        return customersRepository.findAll();
    }
    
}
