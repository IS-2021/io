package com.example.demoio.modules.dailybonus;

public enum DailyBonusState {
    CLAIMED("Odebrano"),
    AVAILABLE("Odbierz"),
    NEXT_BUT_LOCKED("Następny"),
    LOCKED("Zablokowany");

    private final String displayLabel;

    DailyBonusState(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }
}
