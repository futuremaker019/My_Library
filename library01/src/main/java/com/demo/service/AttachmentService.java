package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Attachment;
import com.demo.dto.AttachmentDto;
import com.demo.mapper.AttachmentMapper;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	public List<AttachmentDto> getAttachments(Long id) {
		List<Attachment> attachments = attachmentMapper.getAttachmentList(id);
		
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
	
	public void deleteFileById(Long id) {
		attachmentMapper.deleteById(id);
	}
}
