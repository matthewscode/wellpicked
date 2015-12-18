package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "matchup")
public class Matchup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private int matchupId;

    @Column(name = "matchup_type")
    private String matchupType;

    @ManyToOne
    // this is eager, may have to change
    @JoinColumn(name = "team_1")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team_2")
    private Team team2;

    @Column(name = "winner")
    private int winnerId;

    @Column(name = "loser")
    private int loserId;

    @Column(name = "winner_next_matchup")
    private int winnerNextMatchup;

    @Column(name = "winner_next_team")
    private int winnerNextTeam;

    @Column(name = "loser_next")
    private int loserNextMatchup;

    @Column(name = "loser_next_team")
    private int loserNextTeam;

    @NotNull(message = "enter 0 if you do not know the date")
    @Column(name = "date")
    private int date;

    @Column(name = "weight")
    private int weight;

    @Column(name = "Round")
    private int round;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "t_id")
    private Tournament tournament;

    public Matchup() {
        super();
    }

    public Matchup(String type, Tournament tournament) {
        this.tournament = tournament;
        this.matchupType = type;
    }

    public Matchup(Tournament tournament) {
        this.tournament = tournament;
    }


    public int getMatchupId() {
        return matchupId;
    }

    public void setMatchupId(int matchupId) {
        this.matchupId = matchupId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public int getTournamentId() {
        return tournament.getTournamentId();
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getMatchupType() {
        return matchupType;
    }

    public void setMatchupType(String matchupType) {
        this.matchupType = matchupType;
    }

    public int getWinnerNextMatchup() {
        return winnerNextMatchup;
    }

    public void setWinnerNextMatchup(int winnerNextMatchup) {
        this.winnerNextMatchup = winnerNextMatchup;
    }

    public int getWinnerNextTeam() {
        return winnerNextTeam;
    }

    public void setWinnerNextTeam(int winnerNextTeam) {
        this.winnerNextTeam = winnerNextTeam;
    }

    public int getLoserNextMatchup() {
        return loserNextMatchup;
    }

    public void setLoserNextMatchup(int loserNextMAtchup) {
        this.loserNextMatchup = loserNextMAtchup;
    }

    public int getLoserNextTeam() {
        return loserNextTeam;
    }

    public void setLoserNextTeam(int loserNextTeam) {
        this.loserNextTeam = loserNextTeam;
    }

    public int getLoserId() {
        return loserId;
    }

    public void setLoserId(int loserId) {
        this.loserId = loserId;
    }

}
