package com.puppey.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private int teamId;

    @NotEmpty(message = "Please enter the team name")
    @Column(name = "team_name", unique = true)
    private String teamName;
    
    @Column(name = "bio")
    @Lob
    private String teamBio;

    @Size(min = 2, max = 50, message = "Team slug must be between 2 and 20 characters")
    @Column(name = "team_slug", length = 20)
    private String teamSlug;

    @Size(min = 2, max = 6, message = "Team abbreviation must be between 2 and 6 characters")
    @Column(name = "team_abbr", length = 6)
    private String teamAbbr;

    @Column(name = "color")
    private String color;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @Column(name = "steam_id")
    private String steamId;

    @Column(name = "liquid_tag")
    private String liquidTag;
    
    @Column(name = "twitch_tag")
    private String twitchTag;
    
    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Size(min = 2, max = 6, message = "region is an accronym")
    @Column(name = "region")
    private String region;

    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);
    
    @ManyToMany
    @JoinTable(name = "user_favorite_teams", joinColumns = { @JoinColumn(name = "team_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> usersWhoFavor;
    
    @ManyToMany
    @JoinTable(name = "team_tournament", joinColumns = { @JoinColumn(name = "team_id") }, inverseJoinColumns = { @JoinColumn(name = "tournament_id") })
    private List<Tournament> teamTournaments;
    
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    @Override
    public String toString() {
        return teamName;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    public String getTeamSlug() {
        return teamSlug;
    }

    public void setTeamSlug(String teamSlug) {
        this.teamSlug = teamSlug;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getLiquidTag() {
        return liquidTag;
    }

    public void setLiquidTag(String liquidTag) {
        this.liquidTag = liquidTag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<User> getUsersWhoFavor() {
        return usersWhoFavor;
    }

    public void setUsersWhoFavor(List<User> usersWhoFavor) {
        this.usersWhoFavor = usersWhoFavor;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTwitchTag() {
        return twitchTag;
    }

    public void setTwitchTag(String twitchTag) {
        this.twitchTag = twitchTag;
    }

	public String getTeamBio() {
		return teamBio;
	}

	public void setTeamBio(String teamBio) {
		this.teamBio = teamBio;
	}

    public List<Tournament> getTeamTournaments() {
        return teamTournaments;
    }

    public void setTeamTournaments(List<Tournament> teamTournaments) {
        this.teamTournaments = teamTournaments;
    }
    
    

}
