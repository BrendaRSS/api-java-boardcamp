package com.api.boardcamp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.boardcamp.dtos.GameDTO;
import com.api.boardcamp.exceptions.GameNameConflictException;
import com.api.boardcamp.exceptions.GameNameNotNullException;
import com.api.boardcamp.exceptions.GamePriceNotNullException;
import com.api.boardcamp.exceptions.GameStockNotNullException;
import com.api.boardcamp.models.GameModel;
import com.api.boardcamp.repositories.GameRepository;

@Service
public class GameService {

    final GameRepository gameRepository;

    GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findAll(){
        return gameRepository.findAll();
    }
    
    public GameModel save(GameDTO body){

        if(body.getName().isEmpty()){
            throw new GameNameNotNullException("The name cannot be empty or null");
        }

        if(body.getStockTotal() == 0 ){
            throw new GameStockNotNullException( "The stock cannot be zero or null");
        }

         if(body.getPricePerDay() == 0){
            throw new GamePriceNotNullException("The price cannot be zero or null");
        }

        if(gameRepository.existsByName(body.getName())){
            throw new GameNameConflictException("This name already exists");
        }

        GameModel game = new GameModel(body);
        return gameRepository.save(game);

    }
}
