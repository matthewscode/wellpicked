package com.puppey.service;

import java.util.List;

import com.puppey.domain.Team;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.dto.TournamentDto;

public interface TeamService {

    public boolean addTeam(Team team);

    public boolean updateTeam(Team team);

    public List<Team> getAllTeams();

    public Team getTeam(int teamId);

    public boolean validateNewTeam(Team newTeam);

    void deleteTeam(Team team);

    void addUser(User user, Team team);

    public Team getTeamByIdWithUsers(int teamId);

	public Team getTeamBySlug(String teamSlug);

    public List<Team> getAllDisplayTeams();

    void removeUser(User user, Team team);

    public List<Tournament> getTeamTournaments(int teamId);

    public List<TournamentDto> getTeamTournamentDtoList(int teamId);
}
