package com.example.demoio.modules.dailytasks.repositories;

import com.example.demoio.models.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
    Optional<DailyTask> findById(Long taskId);
}
