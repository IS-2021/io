package com.example.demoio.modules.dailybonus.dto;

import com.example.demoio.modules.dailybonus.DailyBonusState;
import com.example.demoio.modules.dailybonus.DailyBonusType;

public record DailyBonusDTO(int day, int count, DailyBonusType type, DailyBonusState state, boolean isCoinReward) {
}
