package com.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CollectionRequestDto {
	
	private Long collection_id;
	private Long book_id;
	private String content;
}
