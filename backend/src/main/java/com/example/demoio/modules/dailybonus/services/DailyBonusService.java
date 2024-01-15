package com.example.demoio.modules.dailybonus.services;

import com.example.demoio.core.auth.repositories.UserRepository;
import com.example.demoio.core.auth.services.UserProvider;
import com.example.demoio.models.User;
import com.example.demoio.modules.dailybonus.DailyBonusState;
import com.example.demoio.modules.dailybonus.DailyBonusType;
import com.example.demoio.modules.dailybonus.dto.DailyBonusDTO;
import com.example.demoio.modules.dailybonus.models.DailyBonus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DailyBonusService {
    private final UserProvider userProvider;
    private final UserRepository userRepository;

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
    private final List<DailyBonus> allDailyBonuses = Stream.concat(dailyBonusFirstWeek.stream(), dailyBonusSecondWeek.stream()).toList();

    public DailyBonusService(UserProvider userProvider, UserRepository userRepository) {
        this.userProvider = userProvider;
        this.userRepository = userRepository;
    }

    public String getButtonTextFromBonusState(DailyBonusState state) {
        switch (state) {
            case CLAIMED:
                return "Odebrano";
            case AVAILABLE:
                return "Odbierz";
            case LOCKED:
            default:
                return "Zablokowany";
        }
    }

    private static DailyBonusState getDailyBonusState(User user, int day) {
        int claimedBonuses = user.getClaimedBonusCount();
        DailyBonusState state;

        if (day < claimedBonuses) {
            state = DailyBonusState.CLAIMED;
        } else if (user.isEligibleForBonus() && day == claimedBonuses) {
            state = DailyBonusState.AVAILABLE;
        } else {
            state = DailyBonusState.LOCKED;
        }

        return state;
    }

    public List<DailyBonusDTO> getDailyBonuses() {
        User user = userProvider.getCurrentUser();

        List<DailyBonusDTO> toReturn = new ArrayList<>();
        for (int i = 0; i < allDailyBonuses.size(); i++) {
            DailyBonus dailyBonus = allDailyBonuses.get(i);
            DailyBonusState state = getDailyBonusState(user, i);

            DailyBonusDTO dailyBonusDTO = new DailyBonusDTO(
                    i + 1,
                    dailyBonus.count(),
                    dailyBonus.type(),
                    state,
                    dailyBonus.type() == DailyBonusType.COINS
            );
            toReturn.add(dailyBonusDTO);
        }

        return toReturn;
    }

    public void claimUserBonus(int day) {
        User user = this.userProvider.getCurrentUser();

        DailyBonus dailyBonus = allDailyBonuses.get(day - 1);
        if (dailyBonus.type() == DailyBonusType.POINTS) {
            user.setExtraPoints(user.getExtraPoints() + dailyBonus.count());
        } else {
            user.setUserCoins(user.getUserCoins() + dailyBonus.count());
        }

        user.setClaimedBonusCount(user.getClaimedBonusCount() + 1);
        user.setLastBonusClaimedOn(LocalDateTime.now());

        userRepository.save(user);
    }
}
