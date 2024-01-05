package com.example.demoio.modules.dailytasks.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoio.models.User;
import com.example.demoio.models.UserDailyTask;

@Repository
public interface UserDailyTaskRepository extends JpaRepository<UserDailyTask, Long> {

    boolean existsByUserAndDailyTaskDate(User user, LocalDate date);

    List<UserDailyTask> findByUserAndDailyTaskDate(User user, LocalDate date);
}
