package com.puppey.service;

import java.util.List;

import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

public interface TournamentPredictionAchievements {
    
    public User addBracket(User user, List<TournamentPrediction> tournamentPredictions);
    
    public User addSecondBracket(User user, List<TournamentPrediction> userTournamentPredictions);

    void allChecks(int userId);

    public User perfectEight(TournamentPrediction tp);
}
