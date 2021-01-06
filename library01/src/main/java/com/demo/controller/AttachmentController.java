package com.demo.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.AttachmentDto;
import com.demo.service.AttachmentService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class AttachmentController {
	
	private final static String rootDerictory = "/usr/local/tomcat/upload";
	
	@Autowired
	private AttachmentService attachmentService;
	
	@ResponseBody
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachmentDto>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AttachmentDto> list = new ArrayList<AttachmentDto>();
		
		String uploadFolderPath = getFolderFormat();
		File uploadPath = new File(rootDerictory, uploadFolderPath);
		
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			AttachmentDto attachmentDto = new AttachmentDto();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			attachmentDto.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				attachmentDto.setUuid(uuid.toString());
				attachmentDto.setUploadPath(uploadFolderPath);
				
				list.add(attachmentDto);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return new ResponseEntity<List<AttachmentDto>>(list, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/file/delete")
	public ResponseEntity<String> deleteFile(String fileName, Long id) {
		attachmentService.deleteFileById(id);
		
		try {
			File file = new File(rootDerictory + "/" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body("file deleted : " + fileName);
	}
	
	@ResponseBody
	@GetMapping(value="/file/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		Resource resource = new FileSystemResource(rootDerictory + "/" + fileName);
		
		String resourceName = resource.getFilename();
		String resourceNameWithoutUuid = resourceName.substring(resourceName.indexOf("_")+1); 
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", 
					"attachment; filename=" + new String(resourceNameWithoutUuid.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/files/{board_id}")
	public ResponseEntity<List<AttachmentDto>> files(@PathVariable("board_id") Long board_id ) {
		List<AttachmentDto> attachmentDtos = attachmentService.getAttachments(board_id);
		
		return ResponseEntity.ok().body(attachmentDtos);
	}
	
	private String getFolderFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}
