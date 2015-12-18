package com.puppey.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
	
	@Column(name = "artist_name")
	private String artistName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column (name = "gallery_url")
	private String galleryUrl;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;
	
	@OneToMany(mappedBy = "artist")
    private List<Achievement> achievementArt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGalleryUrl() {
		return galleryUrl;
	}

	public void setGalleryUrl(String galleryUrl) {
		this.galleryUrl = galleryUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Achievement> getAchievementArt() {
		return achievementArt;
	}

	public void setAchievementArt(List<Achievement> achievementArt) {
		this.achievementArt = achievementArt;
	}
	
	
	
	
}
