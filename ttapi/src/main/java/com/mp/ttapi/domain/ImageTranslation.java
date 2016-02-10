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
@Table(name = "ImageTranscription")
public class ImageTranslation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "translation")
	private String transcription;

	@Column(name = "language")
	private String language;
	
	@Column(name = "word_count")
	private int wordCount;
	
	@ManyToOne
	@JoinColumn(name = "image_transcription")
	private ImageTranscription imageTranscription;

	@Column(name = "creation_date", insertable = false, updatable = false)
	private long creation = System.currentTimeMillis() / 1000L;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
			
			
}

