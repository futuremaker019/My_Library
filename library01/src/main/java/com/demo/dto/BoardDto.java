package com.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoardDto {
	
	private Long board_id;
	private String title;
	private String content;
	private String writer;
	private LocalDate updatedDate;
}
