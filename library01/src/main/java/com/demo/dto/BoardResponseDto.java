package com.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

	private Long board_id;
	private String title;
	private String content;
	private LocalDate updatedDate;
	
	private int replyCount;
}
