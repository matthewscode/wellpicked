package com.puppey.service;

import java.util.List;

import com.puppey.domain.Matchup;
import com.puppey.domain.Tournament;
import com.puppey.dto.MatchupDto;

public interface TournamentService {

    public List<Tournament> getAllTournaments();

    public List<Matchup> getTournamentMatchups(Tournament tournament);

    public void deleteTournament(int tid);

    public int addTournament(Tournament tournament);

    public void addMatchup(Matchup matchup);

    public void updateTournamentResults(Tournament tournament);

    public List<Matchup> getMatchupsByTournamentId(int tid);

    public Tournament getTournament(int tid);

    public Tournament getTournamentByMatchupId(int matchupId);

    void updateMatchup(Matchup matchup);

    public int getMatchupWinnerId(Matchup matchup);

    public int getMatchupWeight(Matchup matchup);

    public Matchup getMatchup(int mid);

    public int getFirstMatchup(Matchup newMatchup);

    public int getSecondMatchup(Matchup newMatchup);

    public boolean validateNewTournament(Tournament tournament);

    public boolean validateNewTournamentSlug(Tournament tournament);

    public void updateTournament(Tournament editedTournament);

    public List<Tournament> getCurrentAndUpcomingTournaments();

    public List<Tournament> getCompletedTournaments();

    public Tournament getTournamentBySlug(String tournamentSlug);

    public boolean tournamentHasStarted(Tournament tournament);

    public List<Tournament> getAllTournamentsByCreationDate();

    public List<Tournament> getActiveTournaments();

	public void updateGroupStageResults(Tournament tournament);
	
	public void updateGroupStageMatchup(Matchup matchup);

    public List<Tournament> getCurrentTournaments();

    List<Tournament> getCompletedTournaments(int listSize);

	public List<MatchupDto> getMatchupListDto(int tournamentId);
}
