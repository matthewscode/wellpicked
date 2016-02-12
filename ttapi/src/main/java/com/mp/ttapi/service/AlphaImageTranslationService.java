package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.ImageTranslationDAO;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaImageTranslationService implements ImageTranslationService{

	@Autowired
	private ImageTranscriptionService imageTranscriptionService;
	
	@Autowired
	private ImageTranslationDAO imageTranslationDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createImageTranslation(int transcriptionId, String translationText) {
		ImageTranscription transcription = imageTranscriptionService.getImageTranscription(transcriptionId);
		ImageTranslation it = imageTranslationDao.getImageTranslationByTranscription(transcription);
		if(transcription == null || transcriptionId == 0){
			return false;
		}
		if(it == null || it.getId() == 0){
			it = new ImageTranslation();
		}
		it.setTranslation(translationText);
		it.setImageTranscription(transcription);
		it.setWordCount(translationText.length() - translationText.replaceAll(" ", "").length());
		try{
			imageTranslationDao.addImageTranslation(it);
			return true;
		} catch (Exception e){
			return false;
		}
	}
}
