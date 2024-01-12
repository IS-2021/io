package com.example.demoio.modules.dailybonus.services;

import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.User;
import com.example.demoio.modules.dailybonus.DailyBonusState;
import com.example.demoio.modules.dailybonus.DailyBonusType;
import com.example.demoio.modules.dailybonus.dto.DailyBonusDTO;
import com.example.demoio.modules.dailybonus.models.DailyBonus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DailyBonusService {
    private final UserProvider userProvider;

    private final List<DailyBonus> dailyBonusFirstWeek = List.of(
            new DailyBonus(100, DailyBonusType.POINTS),
            new DailyBonus(150, DailyBonusType.POINTS),
            new DailyBonus(2, DailyBonusType.COINS),
            new DailyBonus(200, DailyBonusType.POINTS),
            new DailyBonus(250, DailyBonusType.POINTS),
            new DailyBonus(300, DailyBonusType.POINTS),
            new DailyBonus(3, DailyBonusType.COINS)

    );
    private final List<DailyBonus> dailyBonusSecondWeek = List.of(
            new DailyBonus(350, DailyBonusType.POINTS),
            new DailyBonus(400, DailyBonusType.POINTS),
            new DailyBonus(450, DailyBonusType.POINTS),
            new DailyBonus(4, DailyBonusType.COINS),
            new DailyBonus(500, DailyBonusType.POINTS),
            new DailyBonus(550, DailyBonusType.POINTS),
            new DailyBonus(5, DailyBonusType.COINS)
    );

    public DailyBonusService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    public String getButtonTextFromBonusState(DailyBonusState state) {
        switch (state) {
            case CLAIMED:
                return "Odebrano";
            case AVAILABLE:
                return "Odbierz";
            case NEXT_BUT_LOCKED:
                return "Następny";
            case LOCKED:
            default:
                return "Zablokowany";
        }
    }

    public List<DailyBonusDTO> getDailyBonuses() {
        List<DailyBonus> dailyBonuses = Stream.concat(dailyBonusFirstWeek.stream(), dailyBonusSecondWeek.stream()).toList();;
        User user = userProvider.getCurrentUser();

        List<DailyBonusDTO> toReturn = new ArrayList<>();
        for (int i = 0; i < dailyBonuses.size(); i++) {
            DailyBonus dailyBonus = dailyBonuses.get(i);
            int day = i + 1;

            DailyBonusState state;
            int daysLoggedIn = user.getDaysLoggedIn();
            if (day == daysLoggedIn + 1) {
                state = DailyBonusState.NEXT_BUT_LOCKED;
            } else if (day > daysLoggedIn + 1) {
                state = DailyBonusState.LOCKED;
            } else if (day == daysLoggedIn) {
                state = DailyBonusState.AVAILABLE;
            } else {
                state = DailyBonusState.CLAIMED;
            }

            DailyBonusDTO dailyBonusDTO = new DailyBonusDTO(
                    day,
                    dailyBonus.count(),
                    dailyBonus.type(),
                    state,
                    dailyBonus.type() == DailyBonusType.COINS
            );
            toReturn.add(dailyBonusDTO);
        }

        return toReturn;
    }
}
