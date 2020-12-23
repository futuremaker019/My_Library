package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.AttachmentDto;
import com.demo.service.UploadService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	@ResponseBody
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachmentDto>> upload(MultipartFile[] uploadFiles) {
		log.info("++++++++++++++++++++++++++++++++");
		log.info(uploadFiles);
		
		for(MultipartFile multipartFile : uploadFiles) {
			log.info(multipartFile.getOriginalFilename());
		}
		
		List<AttachmentDto> attachmentDtos = uploadService.store(uploadFiles);
		
		return ResponseEntity.ok().body(attachmentDtos);
	}
}
