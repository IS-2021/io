package com.example.demoio.modules.ranking.services;

import com.example.demoio.models.Ranking;
import com.example.demoio.models.User;
import com.example.demoio.modules.dailytasks.dto.DisplayRankingData;
import com.example.demoio.core.auth.repositories.UserRepository;
import com.example.demoio.modules.ranking.repositories.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingProvider {
    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;

    public RankingProvider(RankingRepository gameProgressRepository, UserRepository userRepository) {
        this.rankingRepository = gameProgressRepository;
        this.userRepository = userRepository;
    }

    public List<DisplayRankingData> getRankingByGameID(Long gameID) {
        List<Ranking> rankings = this.rankingRepository.findAllByGameGameId(gameID);
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (Ranking ranking : rankings) {
            DisplayRankingData drd = new DisplayRankingData(
                    ranking.getUser().getUsername(),
                    ranking.getBestScore());
            recordList.add(drd);
        }

        return recordList;
    }

    public List<DisplayRankingData> getOverallRankingData() {
        List<User> userRank = this.userRepository.findTop10OrderByTotalUserScoreDesc();
        List<DisplayRankingData> recordList = new ArrayList<>();

        for (User user : userRank) {
            DisplayRankingData drd = new DisplayRankingData(
                    user.getUsername(),
                    this.rankingRepository.sumAllScoresByUserId(user.getUserId()));
            recordList.add(drd);
        }

        return recordList;
    }

    public Integer getUserBestScore(Long gameID, Long userID) {
        return this.rankingRepository.findByGame_GameIdAndUserUserId(gameID, userID).orElseGet(() -> {
            Ranking ranking = new Ranking();
            ranking.setBestScore(0);
            return ranking;
        }).getBestScore();
    }
}
