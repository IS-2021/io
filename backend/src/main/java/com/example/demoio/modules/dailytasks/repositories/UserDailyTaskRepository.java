package com.example.demoio.modules.dailytasks.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoio.models.User;
import com.example.demoio.models.UserDailyTask;

@Repository
public interface UserDailyTaskRepository extends JpaRepository<UserDailyTask, Long> {

    boolean existsByUserAndTakenDate(User user, LocalDate date);

    List<UserDailyTask> findByUserAndTakenDate(User user, LocalDate date);

    Optional<UserDailyTask> findByUserAndIsCompleted(User user, boolean isCompleted);

    List<UserDailyTask> findByUser(User user);
}
