package com.puppey.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "news")
public class News {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int newsId;
	
	@Column(name = "creation", updatable = false)
	private Integer creation = (int) (System.currentTimeMillis() / 1000);
	
	@NotBlank(message = "Please enter the News title")
	@Column(name = "title")
	private String newsTitle;
	
	@Lob
	@Column(name = "text")
	private String newsText;
	

	@Column(name = "class")
	private String newsClass;
	
	@Column(name = "slug")
	private String slug;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "confirmed")
	boolean confirmed = false;

	public Integer getCreation() {
		return creation;
	}

	public void setCreation(Integer creation) {
		this.creation = creation;
	}
	
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsText() {
		return newsText;
	}

	public void setNewsText(String newsText) {
		this.newsText = newsText;
	}

	public String getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(String newsClass) {
		this.newsClass = newsClass;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	
}
