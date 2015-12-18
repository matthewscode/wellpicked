package com.puppey.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tournament_prediction")
public class TournamentPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int tournamentPredictionId;

    @Column(name = "tp_name")
    private String tournamentPredictionName;

    @Column(name = "score")
    private int tournamentPredictionScore;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "t_id")
    private Tournament tournament;

    @ManyToMany(mappedBy = "tournamentPredictions")
    private List<Group> groups;

    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournamentPrediction")
    private List<MatchupPrediction> matchupPredictionList;

    @JsonManagedReference
    @OneToMany(mappedBy = "tournamentPrediction")
    private List<Comment> commentList;
    
    @Override
    public String toString() {
        return tournamentPredictionName;
    }
    
    public List<MatchupPrediction> getMatchupPredictionList() {
        return matchupPredictionList;
    }

    @JsonProperty
    public void setMatchupPredictionList(List<MatchupPrediction> listOfMatchupPredictions) {
        this.matchupPredictionList = listOfMatchupPredictions;
    }

    public int getTournamentPredictionId() {
        return tournamentPredictionId;
    }

    public void setTournamentPredictionId(int tournamentPredictionId) {
        this.tournamentPredictionId = tournamentPredictionId;
    }

    public int getTournamentPredictionScore() {
        return tournamentPredictionScore;
    }

    public void setTournamentPredictionScore(int tournamentPredictionScore) {
        this.tournamentPredictionScore = tournamentPredictionScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    public String getTournamentPredictionName() {
        return tournamentPredictionName;
    }

    public void setTournamentPredictionName(String tournamentPredictionName) {
        this.tournamentPredictionName = tournamentPredictionName;
    }
    @JsonIgnore
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    

}