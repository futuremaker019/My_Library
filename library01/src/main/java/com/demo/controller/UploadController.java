package com.demo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	private final static String uploadFolder = "C:\\upload\\image";
	
	@Autowired
	private UploadService uploadService;
	
	@ResponseBody
	@PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachmentDto>> uploadAjaxPost(MultipartFile[] uploadFile) {
		
		List<AttachmentDto> list = new ArrayList<AttachmentDto>();
		log.info("upload ajax post........");
		
		// 날짜를 디렉토리로 구분해여 path를 만들어준다.
		String uploadFolderPath = getFolderFormat();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path in uploadAjaxAction: " + uploadPath);
		
		// 날짜로 들어오는 디렉토리가 없으면 만들어준다.
		if (!uploadPath.exists()) {
			// make yyyy/MM/dd folder
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("---------------------");
			log.info("Upload File Name in uploadAjaxAction : " + multipartFile.getOriginalFilename());
			log.info("Upload File size in uploadAjaxAction : " + multipartFile.getSize());
			
			AttachmentDto attachDTO = new AttachmentDto();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			// 파일의 이름을 객체에 set
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				// 파일의 uuid와 uploadPath를 set
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				list.add(attachDTO);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} // end for loop
		
		return new ResponseEntity<List<AttachmentDto>>(list, HttpStatus.OK);
	}
	
	// 날짜로 파일 경로를 만드는 메서드
	private String getFolderFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}
