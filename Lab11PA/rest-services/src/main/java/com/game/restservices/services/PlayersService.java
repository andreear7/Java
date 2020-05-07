package com.game.restservices.services;
import com.game.restservices.models.Player;
import com.game.restservices.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlayersService {
    @Autowired
    private PlayersRepository repository;

    public List<Player> getAllPlayers() {
        List<Player> players = repository.findAll();
        if (players.size() > 0) {
            return players;
        } else {
            System.out.println("gol");
            return new ArrayList<>();

        }
    }

    public Player save(Player player, String name) {
        player.setId(UUID.randomUUID().toString());
        player.setName(name);
        repository.save(player);
        return player;
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Player changeName(Player player, String id) {
        return repository.findById(id)
                .map(player1 -> {
                    player1.setName(player.getName());
                    return repository.save(player1);
                })
                .orElseGet(() -> {
                    player.setId(id);
                    return repository.save(player);
                });
    }

}
