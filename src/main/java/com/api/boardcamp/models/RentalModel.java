package com.api.boardcamp.models;

import java.time.LocalDate;

import com.api.boardcamp.dtos.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {

    public RentalModel(RentalDTO rentalDTO, CustomersModel customer, GameModel game) {
        this.customer = customer;
        this.game = game;
        this.rentDate = LocalDate.now();
        this.daysRented = rentalDTO.getDaysRented();
        this.returnDate = null;
        this.originalPrice = (rentalDTO.getDaysRented() * game.getPricePerDay());
        this.delayFee = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomersModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;

    @Column
    private LocalDate rentDate;

    @Column
    private int daysRented;

    @Column
    private LocalDate returnDate; 

    @Column
    private int originalPrice;

    @Column
    private int delayFee;
}
