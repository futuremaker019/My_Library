package com.demo.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.AttachmentResponseDto;
import com.demo.dto.DownloadDto;
import com.demo.service.AttachmentService;
import com.demo.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
public class AttachmentController {
	
	private final AttachmentService attachmentService;
	private final FileService fileService;
	
	@ResponseBody
	@GetMapping("/files/{board_id}")
	public ResponseEntity<List<AttachmentResponseDto>> getFiles(@PathVariable("board_id") Long board_id ) {
		List<AttachmentResponseDto> attachmentDtos = attachmentService.getAttachments(board_id);
		
		return ResponseEntity.ok().body(attachmentDtos);
	}
	
	@ResponseBody
	@GetMapping(value="/file/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		DownloadDto downloadDto = fileService.downloadFile(fileName);
		
		return new ResponseEntity<>(downloadDto.getResouce(), downloadDto.getHeaders(), HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "/files/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachmentResponseDto>> uploadFiles(MultipartFile[] uploadFile) {
		
		return ResponseEntity.ok().body(fileService.storeFiles(uploadFile));
	}
	
	@ResponseBody
	@PostMapping("/file/delete")
	public ResponseEntity<String> deleteFile(String fileName, Long id) {
		if(attachmentService.deleteFileById(id)) {
			fileService.deleteFile(fileName);
		}
		
		return ResponseEntity.ok().build();
	}
}
