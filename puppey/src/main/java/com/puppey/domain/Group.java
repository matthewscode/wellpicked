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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "group_name")
    private String groupName;

    @Size(min = 0, max = 500)
    @Column(name = "group_description")
    private String groupDescription;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner")
    private User user;

    @Column(name = "featured", columnDefinition = "boolean default false")
    private boolean featured;

    @NotNull
    @Column(name = "creation", updatable = false)
    private Integer creation = (int) (System.currentTimeMillis() / 1000);

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "group_tournament_predictions", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "tp_id") })
    private List<TournamentPrediction> tournamentPredictions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_user_group", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> userList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tournament_user_group", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "tournament_id") })
    private List<Tournament> tournamentList;

    @JsonIgnore
    @OneToMany(mappedBy = "userGroup")
    private List<Prize> groupPrizes;
    
    @NotNull
    @Column(name = "entry_cost",  columnDefinition = "int default 5000")
    private int entryCost;
    
    @Column(name = "deleted",  columnDefinition = "boolean default false")
    private boolean deleted;
    
    @Override
    public String toString(){
    	return ((Integer) groupId).toString();
    }
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCreation() {
        return creation;
    }

    public void setCreation(Integer creation) {
        this.creation = creation;
    }

    public List<TournamentPrediction> getTournamentPredictions() {
        return tournamentPredictions;
    }

    public void setTournamentPredictions(List<TournamentPrediction> tournamentPredictions) {
        this.tournamentPredictions = tournamentPredictions;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

	public List<Prize> getGroupPrizes() {
		return groupPrizes;
	}

	public void setGroupPrizes(List<Prize> groupPrizes) {
		this.groupPrizes = groupPrizes;
	}
    public String getGroupDescription() {
        return groupDescription;
    }
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
	public int getEntryCost() {
		return entryCost;
	}
	public void setEntryCost(int entryCost) {
		this.entryCost = entryCost;
	}
    
    
    
}
