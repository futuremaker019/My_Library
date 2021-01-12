package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.demo.domain.Attachment;
import com.demo.dto.AttachmentResponseDto;
import com.demo.mapper.AttachmentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@RequiredArgsConstructor
public class AttachmentService {
	
	private final AttachmentMapper attachmentMapper;
	
	// 게시판 글의 id로 해당 글과 연관된 첨부파일을 데이터베이스에서 가져옴
	public List<AttachmentResponseDto> getAttachments(Long board_id) {
		List<Attachment> attachments = attachmentMapper.getAttachmentList(board_id);
		
		List<AttachmentResponseDto> attachmentDtos = null; 
		if(attachments != null) {
			attachmentDtos =  attachments.stream()
					.map(attachment -> AttachmentResponseDto.builder()
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
	public Boolean deleteFileById(Long id) {
		return attachmentMapper.deleteById(id);
	}
}
