package com.puppey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.TournamentDao;
import com.puppey.domain.Matchup;
import com.puppey.domain.Team;
import com.puppey.domain.Tournament;
import com.puppey.dto.MatchupDto;
import com.puppey.dto.TeamDto;
import com.puppey.dto.TournamentDto;
import com.puppey.thread.ThreadStarter;
import com.puppey.util.Utility;

@Service("tournamentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentDao tournamentDao;
    @Autowired
    private TournamentCreationService tournamentCreationService;
    @Autowired
    private TeamService teamService;
    
    @Override
    @Transactional
    public List<Tournament> getAllTournaments() {
        return tournamentDao.getAllTournaments();
    }

    @Override
    @Transactional
    public List<Matchup> getTournamentMatchups(Tournament tournament) {
        return tournamentDao.getTournamentMatchups(tournament);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTournament(int tid) {
        tournamentDao.deleteTournament(getTournament(tid));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int addTournament(Tournament tournament) {
        tournament.setTournamentSlug(Utility.slugify(tournament.getTournamentName()));
        tournamentDao.addTournament(tournament);
        Tournament tournamentAdded = tournamentDao.getTournamentBySlugNoMatchups(tournament.getTournamentSlug());
        tournamentCreationService.createTemplate(tournamentAdded);
        return tournamentAdded.getTournamentId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMatchup(Matchup matchup) {
        // only execute this if both teams are not NONE
        if (matchup.getTeam1().getTeamId() != 0 && matchup.getTeam2().getTeamId() != 0) {
            // set loser
            if (matchup.getWinnerId() == matchup.getTeam1().getTeamId()) {
                // loser is team 2
                matchup.setLoserId(matchup.getTeam2().getTeamId());
            } else if (matchup.getWinnerId() == matchup.getTeam2().getTeamId()) {
                // loser is team 1
                matchup.setLoserId(matchup.getTeam1().getTeamId());
            }

            tournamentDao.updateMatchup(matchup);
        }
        // place winner
        if (matchup.getWinnerId() != 0 && matchup.getWinnerNextMatchup() != 0) {
            Matchup winnerMatchup = tournamentDao.getMatchup(matchup.getWinnerNextMatchup());

            if (matchup.getWinnerNextTeam() == 1) {
                winnerMatchup.setTeam1(teamService.getTeam(matchup.getWinnerId()));
            } else if (matchup.getWinnerNextTeam() == 2) {
                winnerMatchup.setTeam2(teamService.getTeam(matchup.getWinnerId()));
            }
            tournamentDao.updateMatchup(winnerMatchup);
        }

        if (matchup.getLoserNextMatchup() != 0) {
            Matchup loserMatchup = tournamentDao.getMatchup(matchup.getLoserNextMatchup());
            if (matchup.getLoserNextTeam() == 1) {
                loserMatchup.setTeam1(teamService.getTeam(matchup.getLoserId()));
            } else if (matchup.getLoserNextTeam() == 2) {
                loserMatchup.setTeam2(teamService.getTeam(matchup.getLoserId()));
            }

            tournamentDao.updateMatchup(loserMatchup);
        }
    }
    
    @Override
    @Transactional
    public int getFirstMatchup(Matchup winnerMatchup) {
        return tournamentDao.getFirstPriorMatchup(winnerMatchup);
    }

    @Override
    @Transactional
    public int getSecondMatchup(Matchup winnerMatchup) {
        return tournamentDao.getSecondPriorMatchup(winnerMatchup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTournamentResults(Tournament tournament) {

        for (Matchup matchup :  tournament.getMatchups()) {
        	if(matchup.getWinnerId() == 0){
        	}
            updateMatchup(matchup);
            
            Team team1 = teamService.getTeam(matchup.getTeam1().getTeamId());
            Team team2 = teamService.getTeam(matchup.getTeam2().getTeamId());
            
            int i = 0;
            int j = 0;
            for(Tournament team1Tournament : team1.getTeamTournaments()){
                if(team1Tournament.getTournamentId() == tournament.getTournamentId()){
                    i++;
                }
            }
            for(Tournament team2Tournament : team2.getTeamTournaments()){
                if(team2Tournament.getTournamentId() == tournament.getTournamentId()){
                    j++;
                }
            }
            
            if(i == 0 && team1.getTeamId() !=0){
                team1.getTeamTournaments().add(tournament);
                teamService.updateTeam(team1);
            }
            if(j == 0 && team2.getTeamId() !=0){
                team2.getTeamTournaments().add(tournament);
                teamService.updateTeam(team2);
            }
        }
        if(!ThreadStarter.getTournamentScoreQueue().contains(tournament.getTournamentId())){
        ThreadStarter.getTournamentScoreQueue().add(tournament.getTournamentId());
        }
    }

    @Override
    @Transactional
    public List<Matchup> getMatchupsByTournamentId(int tid) {
        return tournamentDao.getMatchupsByTournamentId(tid);
    }

    @Override
    @Transactional
    public Tournament getTournament(int tid) {
    	Tournament tournament = tournamentDao.getTournament(tid);
    	tournament.getMatchups().size();
    	return tournament;
    }

    @Override
    @Transactional
    public Matchup getMatchup(int mid) {
        return tournamentDao.getMatchup(mid);
    }

    @Override
    @Transactional
    public Tournament getTournamentByMatchupId(int matchupId) {
        return tournamentDao.getTournamentByMatchupId(matchupId);
    }

    @Override
    public void addMatchup(Matchup matchup) {
        tournamentDao.addMatchup(matchup);
    }

    @Override
    @Transactional
    public int getMatchupWinnerId(Matchup matchup) {
        return tournamentDao.getMatchupWinnerId(matchup);
    }

    @Override
    @Transactional
    public int getMatchupWeight(Matchup matchup) {
        return tournamentDao.getMatchupWeight(matchup);
    }

    @Override
    @Transactional
    public boolean validateNewTournament(Tournament tournament) {
        return !tournamentDao.tournamentNameExists(tournament.getTournamentName());
    }

    @Override
    @Transactional
    public boolean validateNewTournamentSlug(Tournament tournament) {
        return !tournamentDao.tournamentSlugExists(tournament.getTournamentSlug());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTournament(Tournament editedTournament) {
        tournamentDao.updateTournament(editedTournament);
    }

    @Override
    @Transactional
    public List<TournamentDto> getLatestTournaments(int numResults) {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        List<Tournament> tournaments = tournamentDao.getLatestTournaments(numResults, currentTime);
        List<TournamentDto> dtoList = new ArrayList<>();
        for(Tournament tourn : tournaments){
        	TournamentDto tdto = new TournamentDto();
        	tdto.setTournamentName(tourn.getTournamentName());
        	tdto.setTournamentSlug(tourn.getTournamentSlug());
        	tdto.setTournamentId(tourn.getTournamentId());
        	tdto.setTournamentStart(tourn.getTournamentStart());
        	tdto.setTournamentEnd(tourn.getTournamentEnd());
        	dtoList.add(tdto);
        }
        return dtoList;
    }
    
    @Override
    @Transactional
    public List<Tournament> getCompletedTournaments(int listSize){
        return tournamentDao.getLatestCompletedTournaments(listSize);
    }

    @Override
    @Transactional
    public List<Tournament> getCompletedTournaments() {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        return tournamentDao.getCompletedTournaments(currentTime);
    }

    @Override
    @Transactional
    public Tournament getTournamentBySlug(String tournamentSlug) {
        return tournamentDao.getTournamentBySlug(tournamentSlug);
    }

    @Override
    public boolean tournamentHasStarted(Tournament tournament) {
         return tournament.getTournamentStart() <= (int) (System.currentTimeMillis() / 1000);
    }

    @Override
    @Transactional
    public List<Tournament> getAllTournamentsByCreationDate() {
        return tournamentDao.getAllTournamentsByCreationDate();
    }

    @Override
    @Transactional
    public List<Tournament> getActiveTournaments() {
        return tournamentDao.getAllActiveTournaments();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateGroupStageResults(Tournament tournament) {
        // update matchups in tournament
        for (int i = 0; i < tournament.getMatchups().size(); i++) {
            updateGroupStageMatchup(tournament.getMatchups().get(i));
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateGroupStageMatchup(Matchup matchup) {
		if(matchup.getWinnerId()!=0){
	    	matchup.setTeam1(teamService.getTeam(matchup.getWinnerId()));
			tournamentDao.updateMatchup(matchup);
		}
		
	}

    @Override
    @Transactional
    public List<Tournament> getCurrentTournaments() {
        return tournamentDao.getCurrentTournaments();
    }

	@Override
	public List<MatchupDto> getMatchupListDto(int tournamentId) {
		Tournament tournament = getTournament(tournamentId);
		List<MatchupDto> matchupDtoList = new ArrayList<>();
		for(Matchup matchup : tournament.getMatchups()){
			MatchupDto mdto = new MatchupDto();
			mdto.setMatchupId(matchup.getMatchupId());
			mdto.setMatchupType(matchup.getMatchupType());
			mdto.setTeam1Id((matchup.getTeam1() == null)? 0 : matchup.getTeam1().getTeamId());
			mdto.setTeam2Id((matchup.getTeam2() == null)? 0 : matchup.getTeam2().getTeamId());
			mdto.setTeam1Name((matchup.getTeam1() == null)? "None" : matchup.getTeam1().getTeamName());
			mdto.setTeam2Name((matchup.getTeam2() == null)? "None" : matchup.getTeam2().getTeamName());
			mdto.setTeam1Slug((matchup.getTeam1() == null)? "none" : matchup.getTeam1().getTeamSlug());
			mdto.setTeam2Slug((matchup.getTeam2() == null)? "none" : matchup.getTeam2().getTeamSlug());
			mdto.setWinnerSlug((matchup.getWinnerId() == 0)? "none" : teamService.getTeam(matchup.getWinnerId()).getTeamSlug());
			matchupDtoList.add(mdto);
		}
		return matchupDtoList;
	}

	@Override
	public boolean updateMatchupTeam(int matchupId, int teamInt, int teamId) {
		try{
		Matchup matchup = tournamentDao.getMatchup(matchupId);
		if(teamInt == 1){
			matchup.setTeam1(teamService.getTeam(teamId));
			tournamentDao.updateMatchup(matchup);
			return true;
		}else if(teamInt == 2){
			matchup.setTeam2(teamService.getTeam(teamId));
			tournamentDao.updateMatchup(matchup);
			return true;
		}else if(teamInt == 3){
			matchup.setWinnerId(teamId);
			updateMatchup(matchup); //method inside service places winner inside new matchup
			return true;
		}else{
			return false;
		}
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public TournamentDto getTournamentDto(String slug) {
		Tournament tournament = getTournamentBySlug(slug);
		TournamentDto dto = new TournamentDto();
		dto.setTournamentId(tournament.getTournamentId());
		dto.setTournamentName(tournament.getTournamentName());
		dto.setTournamentSlug(tournament.getTournamentSlug());
		dto.setTournamentDesc(tournament.getTournamentDescription());
		dto.setTemplate(tournament.getTemplate().getTemplate());
		return dto;
	}

	@Override
	@Transactional
	public List<TeamDto> getTournamentTeamList(int tournamentId) {
		List<TeamDto> dtoList = new ArrayList<>();
		List<Team> teamList = getTournament(tournamentId).getTeams();
		for(Team team : teamList){
			TeamDto dto = new TeamDto();
			dto.setName(team.getTeamName());
			dto.setId(team.getTeamId());
			dto.setSlug(team.getTeamSlug());
			dto.setRegion(team.getRegion());
			dto.setColor(team.getColor());
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<MatchupDto> getTournamentMatchups(int tournamentId) {
		List<MatchupDto> dtoList = new ArrayList<>();
		List<Matchup> matchupList = getMatchupsByTournamentId(tournamentId);
		for(Matchup matchup: matchupList){
			MatchupDto dto = new MatchupDto();
			dto.setMatchupId(matchup.getMatchupId());
			dto.setDate(matchup.getDate());
			if(matchup.getTeam1() != null){
				dto.setTeam1Id(matchup.getTeam1().getTeamId());
				dto.setTeam1Name(matchup.getTeam1().getTeamName());
				dto.setTeam1Slug(matchup.getTeam1().getTeamSlug());
				dto.setTeam1Color(matchup.getTeam1().getColor());
			}
			if(matchup.getTeam2() != null){
				dto.setTeam2Id(matchup.getTeam2().getTeamId());
				dto.setTeam2Slug(matchup.getTeam2().getTeamSlug());
				dto.setTeam2Name(matchup.getTeam2().getTeamName());
				dto.setTeam2Color(matchup.getTeam2().getColor());
			}
			dtoList.add(dto);
		}
		return dtoList;
	}
}
