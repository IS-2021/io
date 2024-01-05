package com.example.demoio.modules.dailytasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demoio.models.DailyTask;

import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
    Optional<DailyTask> findById(Long taskId);

    @Query("SELECT dt.dailyTaskId FROM DailyTask dt")
    List<Long> findAllDailyTaskIds();
}
