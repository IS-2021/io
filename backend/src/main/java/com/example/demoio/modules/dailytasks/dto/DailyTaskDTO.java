package com.example.demoio.modules.dailytasks.dto;

import com.example.demoio.modules.dailytasks.DailyTaskState;

public record DailyTaskDTO(Long taskId, String gameName, String imageSlugName, String taskDescription, int coinsReward, DailyTaskState taskState) {
}
