package com.demo.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.AttachmentResponseDto;
import com.demo.dto.DownloadDto;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileService {

//	private final static String rootDirectory = "/usr/local/tomcat/upload";
	private final static String rootDirectory = "c:\\upload\\image";

	public List<AttachmentResponseDto> storeFiles(MultipartFile[] uploadFile) {
		List<AttachmentResponseDto> listOfAttachments = new ArrayList<>();

		String directoryPathByDate = getDirectoryPathByDate();
		File uploadPath = new File(rootDirectory, directoryPathByDate);

		createDirectory(uploadPath);

		for (MultipartFile multipartFile : uploadFile) {
			String fileName = multipartFile.getOriginalFilename();

			AttachmentResponseDto attachmentDto = AttachmentResponseDto.builder().fileName(fileName)
					.uploadPath(directoryPathByDate).build();

			String uuid = UUIDGenerator();
			fileName = uuid + "_" + fileName;
			attachmentDto.saveUuid(uuid);

			File saveFile = new File(uploadPath, fileName);

			try {
				multipartFile.transferTo(saveFile);
				listOfAttachments.add(attachmentDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listOfAttachments;
	}

	public DownloadDto downloadFile(String fileName) {
		DownloadDto downloadDto = new DownloadDto();
		Resource resource = new FileSystemResource(rootDirectory + "\\" + fileName);

		String resourceName = resource.getFilename();
		String resourceNameWithoutUuid = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resourceNameWithoutUuid.getBytes("UTF-8"), "ISO-8859-1"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		downloadDto.saveData(resource, headers);
		
		return downloadDto;
	}
	
	// 디렉토리에 저장된 파일을 일괄 삭제
	public void deleteFiles(List<AttachmentResponseDto> attachmentDtos) {
		if(attachmentDtos == null || attachmentDtos.size() == 0) {
			return;
		}
		
		for (AttachmentResponseDto attachment : attachmentDtos) {
			try {
				Path file = Paths.get(rootDirectory +"\\" 
						+ attachment.getUploadPath() + "\\" + attachment.getUuid() + "_" + attachment.getFileName());
				Files.deleteIfExists(file);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
	public void deleteFile(String fileName) {
		try {
			File file = new File(rootDirectory + "\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDirectory(File path) {
		if (!path.exists()) {
			path.mkdirs();
		}
	}

	private String UUIDGenerator() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	private String getDirectoryPathByDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateFormat = sdf.format(date);
		return dateFormat.replace("-", File.separator);
	}
}
