package com.mp.ttapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.ttapi.dto.FileTranslationDTO;
import com.mp.ttapi.service.FileTranslationService;


@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private FileTranslationService fileTranslationService;

	@ResponseBody
	@RequestMapping("/ft/all")
	public List<FileTranslationDTO> tournaments() {
	    return fileTranslationService.getAllFileTranslationsForDisplay();
	}
	
	@ResponseBody
    @RequestMapping(value = "/ic/create/{checksum}")
    public boolean createImageChecksum(@PathVariable("checksum") int checksum){
    	return fileTranslationService.createImageChecksum(checksum);
    }
	
	@ResponseBody
    @RequestMapping(value = "/ft/create/{checksum}/{originUrl}")
    public boolean createFileTranslation(@PathVariable("checksum") int checksum, @PathVariable("originUrl") String originUrl){
    	return fileTranslationService.createFileTranslation(checksum, "a");
    }
}
