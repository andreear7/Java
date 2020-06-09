package com.music.music.repository;
import com.music.music.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {
    List<Song> findByOrderByVoturiDesc();
    Song findById(int id);
}
