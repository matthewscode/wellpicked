package com.mp.ttapi.dto;

public class FileTranslationDTO {
	
	private long id;
	private int checksum;
	private String originUrl;
	private int creationDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
}
