package com.mp.ttapi.dto;


public class FileTranslationDTO {
	
	private long id;
	private int checksumId;
	private int checksum;
	private String originUrl;
	private int creationDate;
	private int transcriptionId;
	private String transcriptionText;
	private int translationId;
	private String translationText;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getChecksumId() {
		return checksumId;
	}
	public void setChecksumId(int checksumId) {
		this.checksumId = checksumId;
	}
	public int getChecksum() {
		return checksum;
	}
	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public int getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(int creationDate) {
		this.creationDate = creationDate;
	}
	public String getTranscriptionText() {
		return transcriptionText;
	}
	public void setTranscriptionText(String transcriptionText) {
		this.transcriptionText = transcriptionText;
	}
	public String getTranslationText() {
		return translationText;
	}
	public void setTranslationText(String translationText) {
		this.translationText = translationText;
	}
	public int getTranscriptionId() {
		return transcriptionId;
	}
	public void setTranscriptionId(int transcriptionId) {
		this.transcriptionId = transcriptionId;
	}
	public int getTranslationId() {
		return translationId;
	}
	public void setTranslationId(int translationId) {
		this.translationId = translationId;
	}
	
}
