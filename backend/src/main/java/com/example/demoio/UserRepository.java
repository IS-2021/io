package com.example.demoio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.userScore DESC")
    List<User> findTop10ByScore();

    User save(User user);

}
