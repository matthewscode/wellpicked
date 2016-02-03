package com.mp.ttapi.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "image_checksum")
public class ImageChecksum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "checksum")
	private int checksum;
	
	@OneToMany(mappedBy = "imageChecksum")
	private List<FileTranslation> fileTranslationList;

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

	public List<FileTranslation> getFileTranslationList() {
		return fileTranslationList;
	}

	public void setFileTranslationList(List<FileTranslation> fileTranslationList) {
		this.fileTranslationList = fileTranslationList;
	}
	
	
	
}
