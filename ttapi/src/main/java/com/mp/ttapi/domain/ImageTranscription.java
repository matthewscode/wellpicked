package com.mp.ttapi.domain;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "image_transcription")
public class ImageTranscription {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		
		@Column(name = "transcription")
		private String transcription;
		
		@Column(name = "word_count")
		private int wordCount;
		
		@Column(name = "creation_date", insertable = false, updatable = false)
		private Integer creation = (int) (System.currentTimeMillis() / 1000);
		
		@OneToMany(mappedBy = "imageTranscription")
		private List<ImageTranslation> imageTranslationList;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "image_checksum", unique = true)
		private ImageChecksum imageChecksum;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		public List<ImageTranslation> getImageTranslationList() {
			return imageTranslationList;
		}

		public void setImageTranslationList(List<ImageTranslation> imageTranslationList) {
			this.imageTranslationList = imageTranslationList;
		}

		public ImageChecksum getImageChecksum() {
			return imageChecksum;
		}

		public void setImageChecksum(ImageChecksum imageChecksum) {
			this.imageChecksum = imageChecksum;
		}
		
	    public Integer getCreation() {
	        return creation;
	    }

	    public void setCreation(Integer creation) {
	        this.creation = creation;
	    }
		
}
