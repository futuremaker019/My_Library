package com.demo.dto;

import java.sql.Date;
import java.util.List;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	
	private String isbn;
	private String title;
	private String thumbnail;
	private String publisher;
	private String url;
	
	private Date datetime;
	
	private List<AuthorVO> authors;
	
	public BookVO toEntity() {
		return BookVO.builder()
				.isbn(isbn)
				.title(title)
				.thumbnail(thumbnail)
				.publisher(publisher)
				.datetime(datetime)
				.url(url)
				.authors(authors)
				.build();
	}
}
