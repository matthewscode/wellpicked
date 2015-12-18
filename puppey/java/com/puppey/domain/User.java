package com.puppey.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Matthew
 *
 */
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id")
    private int userId;

    @NotNull
    @Column(name = "username")
    private String username;

    @Transient
    private String newUsername;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @Transient
    private String oldPassword;

    @Transient
    private String newPassword;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "login_date")
    private Integer loginDate;

    @Column(name = "total_points")
    private int totalPoints;

    @Column(name = "steam_id")
    private String steamId;

    @NotNull
    @Column(name = "currency", nullable = false, columnDefinition = "int default 0")
    private int currency;

    @Column(name = "avatar")
    private String avatarName;
   
    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);
    
    @ManyToMany(mappedBy = "usersWhoFavor")
    private List<Team> favoriteTeams;

    @ManyToMany(mappedBy = "userList")
    private List<Group> groups;

    @ManyToMany
    @JoinTable(name="user_achievement", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="achievement_id")})
    private List<Achievement> achievements;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<TournamentPrediction> tournamentPredictionList;

    @Override
    public String toString() {
        return ((Integer)userId).toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    @JsonIgnore
    public boolean getEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Integer getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Integer loginDate) {
        this.loginDate = loginDate;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @JsonIgnore
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @JsonIgnore
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @JsonIgnore
    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    @JsonIgnore
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    @JsonIgnore
    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @JsonIgnore
    public List<Team> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(List<Team> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }


    public List<TournamentPrediction> getTournamentPredictionList() {
        return tournamentPredictionList;
    }

    public void setTournamentPredictionList(List<TournamentPrediction> tournamnetPredictionList) {
        this.tournamentPredictionList = tournamnetPredictionList;
    }
    
    
    
}