package com.puppey.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.puppey.dao.TeamDao;
import com.puppey.domain.Team;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.dto.TeamDto;
import com.puppey.dto.TournamentDto;

@Service("teamService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    public String slugify(String teamName) {
        return teamName.toLowerCase().replaceAll("\\s", "-").replaceAll("\\.", "").replaceAll("'", "");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean addTeam(Team newTeam) {
    	 if (!validateNewTeam(newTeam)) {
    		 return false;
         }
        newTeam.setTeamSlug(slugify(newTeam.getTeamName()));
        teamDao.addTeam(newTeam);
        return true;
    }

    @Override
    @Transactional
    public List<Team> getAllTeams() {
        return teamDao.getAllActiveTeams();
    }
    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean updateTeam(Team team) {
        if (!getTeam(team.getTeamId()).getTeamName().equals(team.getTeamName()) && !validateNewTeam(team)) {
        	return false;
        }
        team.setTeamSlug(slugify(team.getTeamName()));
        teamDao.updateTeam(team);
        return true;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteTeam(Team team) {
        team.setIsDeleted(true);
        teamDao.updateTeam(team);
    }

    @Override
    @Transactional
    public Team getTeam(int teamId) {
        return teamDao.getTeam(teamId);
    }

    @Override
    @Transactional
    public boolean validateNewTeam(Team newTeam) {
        return !teamDao.getTeamExistsByTeamName(newTeam.getTeamName());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user, Team team){
        team.getUsersWhoFavor().add(user);
        teamDao.updateTeam(team);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeUser(User user, Team team){
        
        Iterator<User> itr = team.getUsersWhoFavor().iterator();
        while(itr.hasNext()){
            User teamUser = itr.next();
            if(teamUser.getUserId() == user.getUserId()){
                itr.remove();
            }
        }
        teamDao.updateTeam(team);
    }

    @Override
    @Transactional
    public Team getTeamByIdWithUsers(int teamId) {
       Team team = teamDao.getTeam(teamId);
       team.getUsersWhoFavor().size();
       return team;
    }

	@Override
	@Transactional
	public Team getTeamBySlug(String teamSlug) {
		return teamDao.getTeamBySlug(teamSlug);
	}

    @Override
    @Transactional
    public List<Team> getAllDisplayTeams() {
        List<Team> teamList = getAllTeams();
        teamList.remove(0);
        return  teamList;
    }

    @Override
    @Transactional
    public List<Tournament> getTeamTournaments(int teamId) {
        Team team = getTeam(teamId);
        team.getTeamTournaments().size();
        return team.getTeamTournaments();  
    }

    @Override
    @Transactional
    public List<TournamentDto> getTeamTournamentDtoList(int teamId) {
        List<TournamentDto> tournamentDtos = new ArrayList<>();
        Team team = getTeam(teamId);
        for(Tournament tournament : team.getTeamTournaments()){
        	TournamentDto tdto = new TournamentDto();
        	tdto.setTournamentId(tournament.getTournamentId());
        	tdto.setTournamentName(tournament.getTournamentName());
        	tdto.setTournamentSlug(tournament.getTournamentSlug());
        	tdto.setTournamentStart(tournament.getTournamentStart());
        	tdto.setTournamentEnd(tournament.getTournamentEnd());
            tournamentDtos.add(tdto);
        }
        return tournamentDtos;
    }

	@Override
	public List<TeamDto> getActiveTeams(int numResults) {
		List<TeamDto> teamDtoList = new ArrayList<>();
		List<Team> teamList = getAllTeams();
		for(Team team : teamList){
			TeamDto td = new TeamDto();
			td.setId(team.getTeamId());
			td.setName(team.getTeamName());
			td.setSlug(team.getTeamSlug());
			td.setRegion(team.getRegion());
			td.setColor(team.getColor());
			teamDtoList.add(td);
		}
		return teamDtoList;
	}
}
