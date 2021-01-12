package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponseDto {
	private Long id;
	private String fileName;
	private String uploadPath;
	private String uuid;
	
	public void saveUuid(String uuid) {
		this.uuid = uuid;
	}
}
