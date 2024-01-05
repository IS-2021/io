package com.example.demoio.modules.dailytasks.services;

import com.example.demoio.models.User;
import com.example.demoio.models.DailyTask;
import com.example.demoio.models.UserDailyTask;
import com.example.demoio.modules.dailytasks.dto.DailyTaskDTO;
import com.example.demoio.modules.dailytasks.repositories.DailyTaskRepository;
import com.example.demoio.modules.dailytasks.repositories.UserDailyTaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserDailyTaskRepository userDailyTaskRepository;

    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserDailyTaskRepository userDailyTaskRepository) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userDailyTaskRepository = userDailyTaskRepository;
    }

    public boolean areDailyTasksGeneratedForUser(User user, LocalDate date) {
        return userDailyTaskRepository.existsByUserAndDailyTaskDate(user, date);
    }

    public List<DailyTaskDTO> getDailyTasksForUser(User user, LocalDate date) {
            List<UserDailyTask> userDailyTasks = userDailyTaskRepository.findByUserAndDailyTaskDate(user, date);

        return userDailyTasks.stream()
                .map(userDailyTask -> new DailyTaskDTO(
                        userDailyTask.getUserDailyTaskId(),
                        userDailyTask.getDailyTask().getName(),
                        userDailyTask.getDailyTask().getDescription(),
                        userDailyTask.getDailyTask().getGame().getName(),
                        userDailyTask.getDailyTask().getCoinsReward().toString()
                ))
                .toList();
    }


    public void saveDailyTasksForUser(User user, LocalDate date, List<DailyTaskDTO> tasks) {
        List<UserDailyTask> userDailyTasks = tasks.stream()
                .map(task -> {
                    DailyTask dailyTask = dailyTaskRepository.findById(task.taskId())
                            .orElseThrow(() -> new EntityNotFoundException("DailyTask not found with Game Name: " + task.gameName()));

                    UserDailyTask userDailyTask = new UserDailyTask();
                    userDailyTask.setUser(user);
                    userDailyTask.setDailyTask(dailyTask);
                    return userDailyTask;
                })
                .toList();

        userDailyTaskRepository.saveAll(userDailyTasks);
    }


}
