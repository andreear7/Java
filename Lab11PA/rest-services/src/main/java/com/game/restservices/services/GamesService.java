package com.game.restservices.services;
import com.game.restservices.exceptions.GameNotFoundException;
import com.game.restservices.models.Game;
import com.game.restservices.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GamesService {
    @Autowired
    private GamesRepository repository;

    public List<Game> getAllGames() {
        List<Game> games = repository.findAll();
        if (games.size() > 0) {
            return games;
        } else {
            System.out.println("gol");
            return new ArrayList<>();

        }
    }
    public Game findById(String id)
    {
        return repository.findById(id)
            .orElseThrow(() -> new GameNotFoundException(id));
    }
    public Game save(Game game, String contentOfTheGame,String date,String result) {
        game.setId(UUID.randomUUID().toString());
        game.setDate(date);
        game.setResult(result);
        game.setContentOfTheGame(contentOfTheGame);
        repository.save(game);
        return game;
    }
}
