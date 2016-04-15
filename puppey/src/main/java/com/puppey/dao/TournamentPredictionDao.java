package com.puppey.dao;

import java.util.List;

import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

public interface TournamentPredictionDao {

    TournamentPrediction getTournamnetPredictionById(int tournamentPredictionId);

    void addMatchupPrediction(MatchupPrediction matchupPrediction);

    void updateMatchupPrediction(MatchupPrediction matchupPrediction);

    List<MatchupPrediction> getMatchupPredictionsByUser(User predictingUser);

    List<MatchupPrediction> getMatchupPredictionsByUserAndTournament(User predictingUser, Tournament tournament);

    boolean userPredictionsForTournamentExists(User user, Tournament tournament);

    void addTournamentPrediction(TournamentPrediction tournamentPrediction);

    TournamentPrediction getTournamentPrediction(User user, Tournament tournament);

    void updateTournamentPrediction(TournamentPrediction tournamentPrediction);

    List<TournamentPrediction> getTopFivePredictionScores(Tournament tournament);

    List<TournamentPrediction> getLastFivePredictions(Tournament tournament);

    List<TournamentPrediction> getLatestPredictions(Tournament tournament, int amount);

    List<TournamentPrediction> getTopPredictions(Tournament tournament, int amount);

    List<TournamentPrediction> getTournamentPredictionsByTournament(Tournament tournament);

    List<TournamentPrediction> getTournamentPredictionsByUser(User user);

    MatchupPrediction getMatchupPredictionById(int matchupPredictionId);

    TournamentPrediction getTournamentPredictionById(int tournamentPredictionId);

    List<TournamentPrediction> getTournamentPredictionsByUserAndTournaments(User currentUser,
            List<Tournament> tournaments);

    List<TournamentPrediction> getTournamentPredictionsForTournamentByUser(
            Tournament tournament, User user);

	List<TournamentPrediction> getLatestTournamentPredictions(int numResults);

	List<TournamentPrediction> getTournamentPredictionsByScore(
			Tournament tournamentOne, int numResults);
}
