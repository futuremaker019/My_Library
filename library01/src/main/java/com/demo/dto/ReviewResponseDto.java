package com.demo.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ReviewResponseDto {

	private Long review_id;
	private Long book_id;
	
	private String content;
	private int rating;
	
	private Date modifieddate;
}
