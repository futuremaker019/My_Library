package com.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SentenceRequestDto {
	
	private Long sentence_id;
	private Long bno;
	private String content;
}
