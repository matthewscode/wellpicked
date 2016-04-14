package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Matchup;
import com.puppey.domain.MatchupWrapper;
import com.puppey.domain.Tournament;

public interface TournamentDao {

    public List<Tournament> getAllTournaments();

    public List<Matchup> getTournamentMatchups(Tournament tournament);

    public void deleteTournament(Tournament tournamentToBeDeleted);

    public void addTournament(Tournament tournament);

    public void addMatchup(Matchup matchup);

    public List<Matchup> getMatchupsByTournamentId(int tid);

    public Tournament getTournament(int tid);

    public void addMatchupWrapper(MatchupWrapper matchupWrapper);

    public Tournament getTournamentByMatchupId(int matchupId);

    public List<Matchup> getFirstRoundMatchups(Tournament tournament, int numFirstRoundMatchups);

    public void updateMatchup(Matchup matchup);

    public int getMatchupWinnerId(Matchup matchup);

    public int getMatchupWeight(Matchup matchup);

    public Matchup getMatchup(int mid);

    public int getFirstPriorMatchup(Matchup winnerMatchup);

    public int getSecondPriorMatchup(Matchup winnerMatchup);

    public boolean tournamentNameExists(String tournamentName);

    public boolean tournamentSlugExists(String tournamentSlug);

    public void updateTournament(Tournament editedTournament);

    public List<Tournament> getLatestTournaments(int numRows, int currentTime);

    public List<Tournament> getCompletedTournaments(int currentTime);

    public Tournament getTournamentBySlug(String tournamentSlug);

    public List<Tournament> getAllTournamentsByCreationDate();

    public List<Tournament> getAllActiveTournaments();

    public Tournament getTournamentBySlugNoMatchups(String tournamentSlug);

    public Tournament getTournamentNoMatchups(int tid);

    public List<Tournament> getCurrentTournaments();

    public List<Tournament> getLatestCompletedTournaments(int tAmount);
}
