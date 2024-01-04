package com.example.demoio.modules.ranking.services;

import com.example.demoio.models.GameProgress;
import com.example.demoio.models.User;
import com.example.demoio.modules.dailytasks.dto.DisplayRankingData;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import com.example.demoio.modules.ranking.repositories.GameProgressRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingProvider {
    private final GameProgressRepository gameProgressRepository;
    private final UserRepository userRepository;

    public RankingProvider(GameProgressRepository gameProgressRepository, UserRepository userRepository) {
        this.gameProgressRepository = gameProgressRepository;
        this.userRepository = userRepository;
    }

    public List<DisplayRankingData> getRankingByGameID(int gameID) {
        Pageable pageable = PageRequest.of(0, 1);
        List<GameProgress> gameProgressesList = this.gameProgressRepository.getProgressByGameId(gameID, pageable);
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (GameProgress gameProgress : gameProgressesList) {
            DisplayRankingData drd = new DisplayRankingData(
                    gameProgress.getUser().getUsername(),
                    gameProgress.getBestScore());
            recordList.add(drd);
        }

        return recordList;
    }

    public List<DisplayRankingData> getOverallRankingData() {
        List<User> userRank = this.userRepository.findTop10ByScore();
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (User user : userRank) {
            DisplayRankingData drd = new DisplayRankingData(
                    user.getUsername(),
                    user.getTotalUserScore());
            recordList.add(drd);
        }

        return recordList;
    }
}
