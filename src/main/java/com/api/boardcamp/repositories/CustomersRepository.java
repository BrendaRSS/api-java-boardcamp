package com.api.boardcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.boardcamp.models.CustomersModel;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersModel, Long>{
    boolean existsByCpf(String cpf);
}
