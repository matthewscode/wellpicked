package com.puppey.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private int itemId;
    
    @Column(name = "name")
    private String itemName;
    
    @Column(name = "slug")
    private String slug;
    
    @Column(name = "rarity")
    private String rarity;
    
    @Column(name = "steam_url")
    private String steamUrl;

    @OneToMany(mappedBy = "item")
    private List<Prize> prizes;
    public int getItemId() {
        return itemId;
    }

    @Override
    public String toString(){
    	return itemName;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

	public String getSteamUrl() {
		return steamUrl;
	}

	public void setSteamUrl(String steamUrl) {
		this.steamUrl = steamUrl;
	}

	public List<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}
    
    
    
    
}
