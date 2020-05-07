package com.game.restservices.repository;

import com.game.restservices.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
    public interface PlayersRepository extends MongoRepository<Player, String> {

    }

