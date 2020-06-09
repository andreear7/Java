package com.music.music.repository;
import com.music.music.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByNume( String nume);

    User findById(int id);
}
