package com.mp.ttapi.dto;

public class ImageChecksumDTO {
	
	private int checksumId;
	private String transcriptionText;
	private int transcriptionNoWords;
	private String translationText;
	private int translationNoWords;
	
	public int getChecksumId() {
		return checksumId;
	}
	public void setChecksumId(int checksumId) {
		this.checksumId = checksumId;
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
	public int getTranscriptionNoWords() {
		return transcriptionNoWords;
	}
	public void setTranscriptionNoWords(int transcriptionNoWords) {
		this.transcriptionNoWords = transcriptionNoWords;
	}
	public int getTranslationNoWords() {
		return translationNoWords;
	}
	public void setTranslationNoWords(int translationNoWords) {
		this.translationNoWords = translationNoWords;
	}

}
