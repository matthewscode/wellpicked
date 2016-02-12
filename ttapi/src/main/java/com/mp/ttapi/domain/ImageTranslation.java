package com.mp.ttapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image_translation")
public class ImageTranslation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "translation")
	private String translation;

	@Column(name = "language")
	private String language;
	
	@Column(name = "word_count")
	private int wordCount;
	
	@ManyToOne
	@JoinColumn(name = "image_transcription")
	private ImageTranscription imageTranscription;

	@Column(name = "creation_date", insertable = false, updatable = false)
	private Integer creation = (int) (System.currentTimeMillis() / 1000);
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ImageTranscription getImageTranscription() {
		return imageTranscription;
	}

	public void setImageTranscription(ImageTranscription imageTranscription) {
		this.imageTranscription = imageTranscription;
	}

	public Integer getCreation() {
		return creation;
	}

	public void setCreation(Integer creation) {
		this.creation = creation;
	}
	
}

