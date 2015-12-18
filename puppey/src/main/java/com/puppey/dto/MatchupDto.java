package com.puppey.dto;

import com.puppey.domain.Team;

public class MatchupDto {

    private int matchupId;
    private int date;
    private Team team1;
    private Team team2;
    private int winnerId;
    
    public MatchupDto(int matchupId, int date, Team team1, Team team2, int winnerId){
        this.matchupId = matchupId;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.winnerId = winnerId;
    }
    
    public int getMatchupId() {
        return matchupId;
    }
    public void setMatchupId(int matchupId) {
        this.matchupId = matchupId;
    }
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public Team getTeam1() {
        return team1;
    }
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public Team getTeam2() {
        return team2;
    }
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public int getWinnerId() {
        return winnerId;
    }
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    
}
