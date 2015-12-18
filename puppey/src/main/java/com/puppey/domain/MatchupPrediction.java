package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "matchup_prediction")
public class MatchupPrediction {
    @Id
    @GeneratedValue
    @Column(name = "p_id")
    private int predictionId;

    @Column(name = "m_id")
    private int matchup;

    @Column(name = "w_id")
    private int winner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tp_id")
    private TournamentPrediction tournamentPrediction;

    public MatchupPrediction() {
        super();
    }

    public MatchupPrediction(TournamentPrediction tournamentPrediction) {
        this.tournamentPrediction = tournamentPrediction;
    }

    public int getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(int predictionId) {
        this.predictionId = predictionId;
    }

    public int getMatchup() {
        return matchup;
    }

    public void setMatchup(int matchup) {
        this.matchup = matchup;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public TournamentPrediction getTournamentPrediction() {
        return tournamentPrediction;
    }

    public void setTournamentPrediction(TournamentPrediction tournamentPrediction) {
        this.tournamentPrediction = tournamentPrediction;
    }

}
