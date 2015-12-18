package com.puppey.dao;

import java.util.List;

import com.puppey.domain.Team;

public interface TeamDao {

    public void addTeam(Team team);

    public List<Team> getAllTeams();

    public void updateTeam(Team team);

    public Team getTeam(int teamId);

    public Boolean getTeamExistsByTeamName(String teamName);

    public List<Team> getAllActiveTeams();

	public Team getTeamBySlug(String teamSlug);

}
