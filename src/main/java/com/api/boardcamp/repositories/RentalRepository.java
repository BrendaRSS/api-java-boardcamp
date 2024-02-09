package com.api.boardcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.boardcamp.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long>{
    
}
