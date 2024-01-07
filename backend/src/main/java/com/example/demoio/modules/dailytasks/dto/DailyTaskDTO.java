package com.example.demoio.modules.dailytasks.dto;

public record DailyTaskDTO(Long taskId,String gameName, String imageSlugName, String taskDescription, int coinsReward) {
}
