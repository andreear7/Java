package com.music.music.repository;
import com.music.music.models.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
   Vote findByIdUtilizatorAndIdMelodie(int idUtilizator,int idMelodie);
}
