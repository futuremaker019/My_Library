package com.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReviewRequestDto {

	private Long book_id;
	private String content;
	private int rating;
}
