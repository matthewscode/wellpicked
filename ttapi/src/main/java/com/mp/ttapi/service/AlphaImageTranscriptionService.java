package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.domain.ImageTranscription;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaImageTranscriptionService implements ImageTranscriptionService{

	@Autowired
	FileTranslationService fileTranslationService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createImageTranscription(int checksumId, String transcriptionText) {
		ImageTranscription it = new ImageTranscription();
		it.setTranscription(transcriptionText);
		it.setImageChecksum(fileTranslationService.getImageChecksum(checksumId));
		it.setWordCount(transcriptionText.length() - transcriptionText.replaceAll(" ", "").length());
		try{
			//imageTranscriptionDao
			return true;
		} catch (Exception e){
			return false;
		}
	}

}
