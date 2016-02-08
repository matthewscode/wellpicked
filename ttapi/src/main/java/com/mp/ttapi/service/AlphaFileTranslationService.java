package com.mp.ttapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mp.ttapi.dao.FileTranslationDAO;
import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.dto.FileTranslationDTO;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AlphaFileTranslationService implements FileTranslationService {
	
	@Autowired
	private FileTranslationDAO fileTranslationDAO;

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
	public ImageChecksum getImageChecksumByChecksum(int checksum) {
		return fileTranslationDAO.getImageChecksumByChecksum(checksum);
	}

	@Override
	public List<FileTranslationDTO> getAllFileTranslationsForDisplay() {
		List<FileTranslation> fileTranslationList = getAllFileTranslations();
		List<FileTranslationDTO> ftDtoList = new ArrayList<>();
		for(FileTranslation ft : fileTranslationList){
			FileTranslationDTO newDto = new FileTranslationDTO();
			newDto.setId(ft.getId());
			if(ft.getImageChecksum()!=null){
			newDto.setChecksum(ft.getImageChecksum().getChecksum());
			}
			newDto.setOriginUrl(ft.getOriginUrl());
			ftDtoList.add(newDto);
		}
		return ftDtoList;
	}

}