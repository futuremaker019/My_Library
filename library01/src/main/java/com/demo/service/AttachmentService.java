package com.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Attachment;
import com.demo.dto.AttachmentDto;
import com.demo.mapper.AttachmentMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AttachmentService {
	
	private final static String rootDirectory = "/usr/local/tomcat/upload";

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	// 게시판 글의 id로 해당 글과 연관된 첨부파일을 데이터베이스에서 가져옴
	public List<AttachmentDto> getAttachments(Long board_id) {
		List<Attachment> attachments = attachmentMapper.getAttachmentList(board_id);
		
		List<AttachmentDto> attachmentDtos = null; 
		if(attachments != null) {
			attachmentDtos =  attachments.stream()
					.map(attachment -> AttachmentDto.builder()
											.id(attachment.getFile_id())
											.fileName(attachment.getFileName())
											.uploadPath(attachment.getUploadPath())
											.uuid(attachment.getUuid())
											.build())
					.collect(Collectors.toList());
		}
		
		return  attachmentDtos;
	}
	
	// 데이터 베이스에 저장된 파일 정보 삭제
	public void deleteFileById(Long id) {
		attachmentMapper.deleteById(id);
	}
	
	// 디렉토리에 저장된 파일을 일괄 삭제
	public void deleteFiles(List<AttachmentDto> attachmentDtos) {
		if(attachmentDtos == null || attachmentDtos.size() == 0) {
			return;
		}
		
		for (AttachmentDto attachment : attachmentDtos) {
			try {
				Path file = Paths.get(rootDirectory 
						+ attachment.getUploadPath() + "/" + attachment.getUuid() + "_" + attachment.getFileName());
				Files.deleteIfExists(file);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
}
