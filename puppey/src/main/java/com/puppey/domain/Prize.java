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


@Entity
@Table(name = "prizes")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prize_id")
    private int prizeId;
    
    @ManyToOne
    @JoinColumn(name = "userGroup")
    private Group userGroup;
    
    @ManyToOne
    @JoinColumn(name = "winner")
    private User winner;
    
    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;
    
    @NotNull
    @Column
    private int place;
    
    @Column(name = "isDistributed", columnDefinition = "boolean default false")
    private boolean isDistributed;

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public Group getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Group userGroup) {
        this.userGroup = userGroup;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public boolean getIsDistributed() {
        return isDistributed;
    }

    public void setIsDistributed(boolean isDistributed) {
        this.isDistributed = isDistributed;
    }

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
    
    
    
    
    
}
