package com.mp.ttapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.FileTranslationDAO;
import com.mp.ttapi.dao.ImageTranscriptionDAO;
import com.mp.ttapi.dao.ImageTranslationDAO;
import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.domain.ImageTranscription;
import com.mp.ttapi.domain.ImageTranslation;
import com.mp.ttapi.dto.FileTranslationDTO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaFileTranslationService implements FileTranslationService {
	
	@Autowired
	private FileTranslationDAO fileTranslationDAO;
	@Autowired
	private ImageTranscriptionDAO imageTranscriptionDao;
	
	@Override
	@Transactional
	public List<FileTranslation> getAllFileTranslations() {
		return fileTranslationDAO.getAllFileTranslations();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createImageChecksum(int checksum) {
		ImageChecksum ic = new ImageChecksum();
		ic.setChecksum(checksum);
		return fileTranslationDAO.createImageChecksum(ic);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createFileTranslation(int checksum, String originUrl) {
		FileTranslation ft = new FileTranslation();
		ImageChecksum ic = getImageChecksumByChecksum(checksum);
		ft.setImageChecksum(ic);
		ft.setOriginUrl(originUrl);
		return fileTranslationDAO.createFileTranslation(ft);
	}
	
	@Override
	@Transactional
	public ImageChecksum getImageChecksum(int id) {
		return fileTranslationDAO.getImageChecksum(id);
	}

	@Override
	@Transactional
	public ImageChecksum getImageChecksumByChecksum(int checksum) {
		return fileTranslationDAO.getImageChecksumByChecksum(checksum);
	}

	@Override
	public List<FileTranslationDTO> getAllFileTranslationsForDisplay() {
		List<FileTranslation> fileTranslationList = getAllFileTranslations();
		List<FileTranslationDTO> ftDtoList = convertFileTranslationsToDTO(fileTranslationList);
		return ftDtoList;
	}
	
	@Override
	@Transactional
	public List<FileTranslationDTO> convertFileTranslationsToDTO(List<FileTranslation> fileTranslationList){
		List<FileTranslationDTO> ftDtoList = new ArrayList<>();
		for(FileTranslation ft : fileTranslationList){
			FileTranslationDTO newFt = new FileTranslationDTO();
			ImageTranscription transcription = imageTranscriptionDao.getImageTranscriptionByChecksumId(ft.getImageChecksum());
			newFt.setId(ft.getId());
			newFt.setChecksumId(ft.getImageChecksum().getId());
			newFt.setChecksum(ft.getImageChecksum().getChecksum());
			newFt.setOriginUrl(ft.getOriginUrl());
			if(transcription == null || transcription.getId() == 0){
				newFt.setTranscriptionId(0);
				newFt.setTranscriptionText("");
				newFt.setTranslationId(0);
				newFt.setTranslationText("");
				ftDtoList.add(newFt);
			}else{
			newFt.setTranscriptionId(transcription.getId());
			newFt.setTranscriptionText(transcription.getTranscription());
			List<ImageTranslation> translationList = transcription.getImageTranslationList();
				if(translationList == null || translationList.size() <= 0){
				newFt.setTranslationId(0);
				newFt.setTranslationText("");
				}else{
					newFt.setTranslationId(translationList.get(0).getId());
					newFt.setTranslationText(translationList.get(0).getTranslation());
				}
			ftDtoList.add(newFt);
			}
		}
		return ftDtoList;
	}

	@Override
	@Transactional
	public List<FileTranslationDTO> getFileTranslationsByRow(int start, int stop) {
		List<FileTranslation> ftList = fileTranslationDAO.getFileTranslationsByRow(start, stop);
		return convertFileTranslationsToDTO(ftList);
	}
}
