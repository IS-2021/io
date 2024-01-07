package com.example.demoio.modules.dailytasks.services;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.DailyTask;
import com.example.demoio.models.UserDailyTask;
import com.example.demoio.modules.dailytasks.dto.DailyTaskDTO;
import com.example.demoio.modules.dailytasks.repositories.DailyTaskRepository;
import com.example.demoio.modules.dailytasks.repositories.UserDailyTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserDailyTaskRepository userDailyTaskRepository;
    private final UserProvider userProvider;

    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserDailyTaskRepository userDailyTaskRepository, UserProvider userProvider) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userDailyTaskRepository = userDailyTaskRepository;
        this.userProvider = userProvider;
    }

    public Optional<UserDailyTask> getUserCurrentDailyTask() {
        return this.userDailyTaskRepository.findByUserAndIsCompleted(userProvider.getCurrentUser(), false);
    }

    /*
    Gets all tasks, that are either not completed or not taken by user.
     */
    public List<DailyTask> getPossibleDailyTasks() {
        List<DailyTask> allDailyTasks = this.dailyTaskRepository.findAll();
        List<UserDailyTask> userTasks = this.userDailyTaskRepository.findByUser(this.userProvider.getCurrentUser());

        List<DailyTask> possibleDailyTasks = allDailyTasks.stream()
                .filter(dailyTask -> userTasks.stream().noneMatch(userDailyTask -> userDailyTask.getDailyTask().equals(dailyTask)))
                .toList();

        return possibleDailyTasks;
    }

    public List<DailyTask> getUserDailyTasks() {
        Optional<UserDailyTask> currentDailyTask = getUserCurrentDailyTask();

        if (currentDailyTask.isPresent()) {
            return List.of(
                    currentDailyTask.get().getDailyTask(),
                    getPossibleDailyTasks().get(0)
            );
        } else {
            return List.of(
                    getPossibleDailyTasks().get(0),
                    getPossibleDailyTasks().get(1)
            );
        }
    }

    public List<DailyTaskDTO> getUserDailyTasksDTO() {
        return getUserDailyTasks().stream()
                .map(dailyTask -> new DailyTaskDTO(
                        dailyTask.getDailyTaskId(),
                        dailyTask.getName(),
                        dailyTask.getGame().getImageSlugName(),
                        dailyTask.getDescription(),
                        dailyTask.getCoinsReward()
                ))
                .toList();
    }

    public void saveDailyTasksForUser(Long dailyTaskId) {
        DailyTask dailyTask = dailyTaskRepository.findById(dailyTaskId).orElseThrow();

        UserDailyTask userDailyTask = new UserDailyTask(this.userProvider.getCurrentUser(), dailyTask);
        userDailyTaskRepository.save(userDailyTask);
    }
}
