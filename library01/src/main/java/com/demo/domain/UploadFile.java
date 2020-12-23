package com.demo.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UploadFile {
	private Long file_id;
    private String fileName;
    private String saveFileName;
    private String uploadPath;
    private String contentType;
    private long size;
    private LocalDateTime registerDate;
    
    private Long board_id;
}
