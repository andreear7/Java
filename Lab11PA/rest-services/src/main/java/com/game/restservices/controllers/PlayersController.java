package com.game.restservices.controllers;
import com.game.restservices.models.Player;
import com.game.restservices.services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab")
public class PlayersController {
    @Autowired
    private PlayersService service;

    @RequestMapping(path = "/players", method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = service.getAllPlayers();
        return new ResponseEntity<List<Player>>(players, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/players")
    Player newPlayer(@RequestBody Player newPlayer) {
        return service.save(newPlayer, newPlayer.getName());
    }


    @DeleteMapping("/players/{id}")
    void deletePlayer(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/players/{id}")
    Player changePlayerName(@RequestBody Player newPlayer, @PathVariable String id) {
        return service.changeName(newPlayer,id);

    }

}

