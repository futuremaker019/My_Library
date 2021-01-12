package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	private Long book_id;
	private String author;
	
	public void saveBookId(Long book_id) {
		this.book_id = book_id;
	}
}
