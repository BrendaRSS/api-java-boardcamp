package com.api.boardcamp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.boardcamp.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long>{
    @Query(value = "SELECT * FROM rentals r WHERE r.game_id = :gameId", nativeQuery = true)
    List<RentalModel> findByGameId(@Param("gameId") Long gameId);
}
