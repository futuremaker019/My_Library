package com.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	
	private String title;
	private String content;
	private String thumbnail;
	
	private List<AttachmentResponseDto> attachments;
}
