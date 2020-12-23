package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
	private Long file_id;
	private String fileName;
	private String uploadPath;
	private String uuid;
	
	private Long board_id;
	
	public Attachment(String fileName, String uploadPath, String uuid) {
		this.fileName = fileName;
		this.uploadPath = uploadPath;
		this.uuid = uuid;
	}
	
	public void setBoard_id(Long board_id) {
		this.board_id = board_id;
	}
}
