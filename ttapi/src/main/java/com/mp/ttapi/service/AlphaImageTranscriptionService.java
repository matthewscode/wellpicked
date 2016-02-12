package com.mp.ttapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.FileTranslationDAO;
import com.mp.ttapi.dao.ImageTranscriptionDAO;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaImageTranscriptionService implements ImageTranscriptionService{

	@Autowired
	private FileTranslationService fileTranslationService;
	
	@Autowired
	private ImageTranscriptionDAO imageTranscriptionDao;
	@Autowired 
	private FileTranslationDAO fileTranslationDao;
	
	@Override
	@Transactional
	public ImageTranscription getImageTranscription(int id) {
		return imageTranscriptionDao.getImageTranscription(id);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createImageTranscription(int checksumId, String transcriptionText) {
		ImageChecksum ic = fileTranslationService.getImageChecksum(checksumId);
		ImageTranscription it = imageTranscriptionDao.getImageTranscriptionByChecksumId(ic);
		if(it == null || it.getId() == 0){
			it = new ImageTranscription();
		}
		it.setTranscription(transcriptionText);
		it.setWordCount(transcriptionText.length() - transcriptionText.replaceAll(" ", "").length());
		it.setImageChecksum(ic);
		imageTranscriptionDao.addImageTranscription(it);
		ic.setImageTranscription(it);
		fileTranslationDao.createImageChecksum(ic);
		return fileTranslationDao.createImageChecksum(ic);
	}
}
