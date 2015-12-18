package com.puppey.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int achievementId;
    

    @Column(name = "slug", columnDefinition = ("VARCHAR(50) NOT NULL UNIQUE"))
    private String achievementSlug;
    
    @NotEmpty
    @Column(name = "name", columnDefinition = ("VARCHAR(50) NOT NULL UNIQUE"))
    private String achievementName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "reward", columnDefinition = "int default 1000")
    private int reward;
    
    @ManyToMany(mappedBy = "achievements")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artist;
    
    @Override
    public String toString(){
    	return achievementName;
    }
    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public String getAchievementSlug() {
        return achievementSlug;
    }

    public void setAchievementSlug(String achievementSlug) {
        this.achievementSlug = achievementSlug;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }
    
    
}
