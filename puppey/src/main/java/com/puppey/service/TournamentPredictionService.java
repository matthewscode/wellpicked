package com.puppey.service;

import java.util.List;

import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.dto.TournamentPredictionDto;
import com.puppey.util.Message;

public interface TournamentPredictionService {

    void addMatchupPredictionsByTournamentId(int tid, User predictingUser);

    void addMatchupPredictionsInWrapper(TournamentPrediction matchupPredictionWrapper);

    List<MatchupPrediction> getMatchupPredictionsByUser(User predictingUser);

    List<MatchupPrediction> getMatchupPredictionsByUserAndTournament(User predictingUser, Tournament tournament);

    void updateMatchupPredictionsInWrapper(TournamentPrediction matchupPredictionWrapper);

    int calculateMatchupPredictionScore(MatchupPrediction matchupPrediction);

    boolean userPredictionsForTournamentExists(User user, Tournament tournament);

    TournamentPrediction addTournamentPrediction(TournamentPrediction tournamentPrediction);

    TournamentPrediction getPredictionTournament(User user, Tournament tournament);

    void updateTournamentPrediction(TournamentPrediction tournamentPrediction);

    List<MatchupPrediction> getMatchupPredictionsByEmail(String name);

    List<TournamentPrediction> getTopFivePredictionScores(Tournament tournament);

    List<TournamentPrediction> getLastFivePredictions(Tournament tournament);

    List<TournamentPredictionDto> getLatestPredictionsForTournament(int tournamentId, int amount);

    List<TournamentPrediction> getTopPredictionsForTournament(int tournamentId, int amount);

    void updateTournamentPredictionScores(Tournament tournament);

    List<TournamentPrediction> getTournamentPredictionsByTournament(Tournament tournament);

    List<TournamentPrediction> getTournamentPredictionsByUser(User user);

    void updateMatchupPrediction(MatchupPrediction matchupPrediction);

    MatchupPrediction getMatchpPredictionById(int matchupPredictionId);

    TournamentPredictionDto getTournamentPredictionById(int tournamentPredictionId);
    
    TournamentPrediction getTournamentPredictionByIdSolo(int tournamentPredictionId);

    List<TournamentPrediction> getTournamentPredictionsByUserAndTournaments(User currentUser,
            List<Tournament> tournaments);

    boolean tournamentPredictionBelongsToUser(int userId, int userId2);

    List<TournamentPredictionDto> getTournamentPredictionForTournamentByUser(
            int tournamentId, int userId);

	void addMatchupPrediction(MatchupPrediction jsonMP);

    TournamentPredictionDto getTournamentPredictionDtoById(int id);

    List<TournamentPredictionDto> getTournamentPredictionDtoForTournamentByUser(int tournamentId, int userId);

    List<TournamentPrediction> getUserTournamentPredictionForTournament(User currentUser, Tournament tournament);
    
    int createNewTournamentPrediction(TournamentPrediction tournamentPrediction);
    
    //int because it needs to return the tp id
    int updateUserTournamentPrediction(TournamentPrediction tournamentPrediction);

    Message updateApiTournamentPrediction(TournamentPrediction jsonTP);

    Message createApiTournamentPrediction(TournamentPrediction jsonTP);

	void updateTournamentPredictionScores(int tournamentId);

    List<TournamentPredictionDto> getPopularPredictionsForTournament(int tournamentId, int amount);

	List<TournamentPredictionDto> getLatestTournamentPredictions(int tournamentId, int numResults);

	boolean createTournamentPrediction(int tournamentId, User user, String predictionName, List<MatchupPrediction> matchupPredictionList);

}
