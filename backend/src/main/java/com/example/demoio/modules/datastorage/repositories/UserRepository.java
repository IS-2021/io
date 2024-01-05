package com.example.demoio.modules.datastorage.repositories;

import com.example.demoio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.rankings r GROUP BY u ORDER BY SUM(r.bestScore) DESC")
    List<User> findTop10OrderByTotalUserScoreDesc();
}
