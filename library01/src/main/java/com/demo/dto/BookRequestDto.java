package com.demo.dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.demo.domain.Author;
import com.demo.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
	
	private String isbn;
	private String title;
	private String thumbnail;
	private String publisher;
	private String url;
	private String userId;
	
	private Date datetime;
	
	private List<Author> authors;
	
	private List<Long> book_ids;
	
	public Book toEntity() {
		return Book.builder()
				.isbn(uuidAddedToIsbn(isbn))
				.title(title)
				.thumbnail(thumbnail)
				.publisher(publisher)
				.datetime(datetime)
				.url(url)
				.authors(authors)
				.build();
	}
	
	private String uuidAddedToIsbn(String isbn) {
        UUID uuid = UUID.randomUUID();
        return isbn + uuid.toString().substring(23);
    }
}
