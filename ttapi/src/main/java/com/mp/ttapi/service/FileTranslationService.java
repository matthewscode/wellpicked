package com.mp.ttapi.service;

import java.util.List;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;
import com.mp.ttapi.dto.FileTranslationDTO;

public interface FileTranslationService {
	
	public List<FileTranslation> getAllFileTranslations();

	public boolean createImageChecksum(int checksum);

	public boolean createFileTranslation(int checksum, String originUrl);
	
	public ImageChecksum getImageChecksumByChecksum(int checksum);

	public List<FileTranslationDTO> getAllFileTranslationsForDisplay();
	
}