package com.mp.ttapi.service;

import com.mp.ttapi.domain.ImageTranscription;

public interface ImageTranscriptionService {
	
	public boolean createImageTranscription(int id, String transcriptionText);
	
	public ImageTranscription getImageTranscription(int id);
}
