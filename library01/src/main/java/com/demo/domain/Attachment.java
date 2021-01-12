package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
	
	public void saveBoard_id(Long board_id) {
		this.board_id = board_id;
	}
}
