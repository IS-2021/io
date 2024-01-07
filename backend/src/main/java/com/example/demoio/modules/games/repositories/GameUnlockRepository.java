package com.example.demoio.modules.games.repositories;

import com.example.demoio.models.GameUnlock;
import com.example.demoio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameUnlockRepository extends JpaRepository<GameUnlock, Long> {

    List<GameUnlock> findByUser(User user);

}
