package com.api.boardcamp.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
    
}
