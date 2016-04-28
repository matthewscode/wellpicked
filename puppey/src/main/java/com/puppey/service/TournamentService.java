package com.puppey.service;

import java.util.List;

import com.puppey.domain.Matchup;
import com.puppey.domain.Tournament;
import com.puppey.dto.MatchupDto;
import com.puppey.dto.TeamDto;
import com.puppey.dto.TournamentDto;

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

    public List<TournamentDto> getLatestTournaments(int numResults);

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

	public boolean updateMatchupTeam(int matchupId, int teamInt, int teamId);

	public TournamentDto getTournamentDto(String slug);

	public List<TeamDto> getTournamentTeamList(int tournamentId);

	public List<MatchupDto> getTournamentMatchups(int tournamentId);
}
