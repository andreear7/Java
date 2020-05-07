package com.game.restservices.repository;
import com.game.restservices.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GamesRepository extends MongoRepository<Game, String> {

}
