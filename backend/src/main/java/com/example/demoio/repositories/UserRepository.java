package com.example.demoio.repositories;

import com.example.demoio.models.orm.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.totalUserScore DESC LIMIT 10")
    List<User> findTop10ByScore();
}
