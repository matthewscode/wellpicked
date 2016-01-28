package com.puppey.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tournament")
public class Tournament {

    @NotEmpty(message = "Please enter the tournament name")
    @Column(name = "t_name")
    private String tournamentName;

    @Column(name = "t_slug")
    private String tournamentSlug;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int tournamentId;

    @Column(name = "t_num_teams")
    private int numTeams;

    @NotNull(message = "enter 0 if you do not know the start")
    @Column(name = "t_start")
    private int tournamentStart;

    @NotNull(message = "enter 0 if you do not know the end")
    @Column(name = "t_end")
    private int tournamentEnd;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "t_desc")
    private String tournamentDescription;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @Column(name = "twitch_tag")
    private String twitchTag;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "tournament")
    private List<Matchup> matchups;

    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);
    
    @Column(name = "finished",  columnDefinition = "boolean default false")
    private boolean finished;
    
    @Column(name = "bracketSet",  columnDefinition = "boolean default false")
    private boolean bracketSet;

    // NEVER DELETE ONLY FLAG AS DELETED - Jen Huang April 2015
    @Column(name = "deleted")
    private int deleted;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "teamTournaments")
    private List<Team> teams;

    @Override
    public String toString(){
    	return tournamentName;
    }
    
    public Tournament() {
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public List<Matchup> getMatchups() {
        return matchups;
    }

    public void setMatchups(List<Matchup> matchups) {
        this.matchups = matchups;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getTournamentStart() {
        return tournamentStart;
    }

    public void setTournamentStart(int tournamentStart) {
        this.tournamentStart = tournamentStart;
    }


    public int getTournamentEnd() {
        return tournamentEnd;
    }

    public void setTournamentEnd(int tournamentEnd) {
        this.tournamentEnd = tournamentEnd;
    }

    public String getTournamentDescription() {
        return tournamentDescription;
    }

    public void setTournamentDescription(String tournamentDescription) {
        this.tournamentDescription = tournamentDescription;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public String getTournamentSlug() {
        return tournamentSlug;
    }

    public void setTournamentSlug(String tournamentSlug) {
        this.tournamentSlug = tournamentSlug;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getTwitchTag() {
        return twitchTag;
    }

    public void setTwitchTag(String twitchTag) {
        this.twitchTag = twitchTag;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

	public boolean isBracketSet() {
		return bracketSet;
	}

	public void setBracketSet(boolean bracketSet) {
		this.bracketSet = bracketSet;
	}

    
    
}
