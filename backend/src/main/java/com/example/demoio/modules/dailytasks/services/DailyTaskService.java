package com.example.demoio.modules.dailytasks.services;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.DailyTask;
import com.example.demoio.models.UserDailyTask;
import com.example.demoio.modules.dailytasks.DailyTaskState;
import com.example.demoio.modules.dailytasks.dto.DailyTaskDTO;
import com.example.demoio.modules.dailytasks.repositories.DailyTaskRepository;
import com.example.demoio.modules.dailytasks.repositories.UserDailyTaskRepository;
import com.example.demoio.modules.games.services.GameUnlockService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserDailyTaskRepository userDailyTaskRepository;
    private final UserProvider userProvider;
    private final GameUnlockService gameUnlockService;

    @Getter
    private final int MinimumScoreToCompleteDailyTask = 500;


    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserDailyTaskRepository userDailyTaskRepository, UserProvider userProvider, GameUnlockService gameUnlockService) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userDailyTaskRepository = userDailyTaskRepository;
        this.userProvider = userProvider;
        this.gameUnlockService = gameUnlockService;
    }

    public Optional<UserDailyTask> getUserCurrentDailyTask() {
        return this.userDailyTaskRepository.findByUserAndIsCompleted(userProvider.getCurrentUser(), false);
    }

    public boolean isCurrentDailyTaskRelatedToGame(Long gameId) {
        Optional<UserDailyTask> currentDailyTask = this.getUserCurrentDailyTask();
        return currentDailyTask.isPresent() && currentDailyTask.get().getDailyTask().getGame().getGameId().equals(gameId);
    }

    public void markCurrentDailyTaskAsCompleted() {
        Optional<UserDailyTask> userDailyTask = this.getUserCurrentDailyTask();
        userDailyTask.ifPresent(task -> {
            task.setIsCompleted(true);
            this.userDailyTaskRepository.save(task);
        });
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
        List<DailyTask> possibleDailyTasks = getPossibleDailyTasks();

        if (currentDailyTask.isPresent()) {
            return List.of(
                    currentDailyTask.get().getDailyTask(),
                    possibleDailyTasks.get(0)
            );
        } else {
            return List.of(
                    possibleDailyTasks.get(0),
                    possibleDailyTasks.get(1)
            );
        }
    }

    private DailyTaskState getTaskState(DailyTask dailyTask) {
        boolean isRelatedToGame = this.isCurrentDailyTaskRelatedToGame(dailyTask.getGame().getGameId());
        boolean userHasActiveDailyTask = this.getUserCurrentDailyTask().isPresent();

        if (userHasActiveDailyTask) {
            UserDailyTask userDailyTask = this.getUserCurrentDailyTask().get();

            if (isRelatedToGame && userDailyTask.getIsCompleted()) {
                return DailyTaskState.COMPLETED;
            }

            if (isRelatedToGame && !userDailyTask.getIsCompleted()) {
                return DailyTaskState.TAKEN;
            }

            if (!isRelatedToGame) {
                return DailyTaskState.DISABLED;
            };
        }

        return DailyTaskState.AVAILABLE;
    }

    public List<DailyTaskDTO> getUserDailyTasksDTO() {
        return getUserDailyTasks().stream()
                .map(dailyTask -> new DailyTaskDTO(
                        dailyTask.getDailyTaskId(),
                        dailyTask.getName(),
                        dailyTask.getGame().getImageSlugName(),
                        dailyTask.getDescription(),
                        dailyTask.getCoinsReward(),
                        getTaskState(dailyTask)
                ))
                .toList();
    }

    public void saveDailyTasksForUser(Long dailyTaskId) {
        if (this.getUserCurrentDailyTask().isPresent()) return;

        DailyTask dailyTask = dailyTaskRepository.findById(dailyTaskId).orElseThrow();

        UserDailyTask userDailyTask = new UserDailyTask(this.userProvider.getCurrentUser(), dailyTask);
        userDailyTaskRepository.save(userDailyTask);

        this.gameUnlockService.unlockGame(dailyTask.getGame());
    }
}
