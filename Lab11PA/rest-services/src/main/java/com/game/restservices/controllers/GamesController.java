package com.game.restservices.controllers;
import com.game.restservices.models.Game;
import com.game.restservices.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lab")
public class GamesController {

    @Autowired
    private GamesService service;

    @RequestMapping(path = "/games", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = service.getAllGames();
        return new ResponseEntity<List<Game>>(games, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    Game one(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/games")
    Game newGame(@RequestBody Game newGame) {
        return service.save(newGame, newGame.getContentOfTheGame(),newGame.getDate(),newGame.getResult());
    }

}
