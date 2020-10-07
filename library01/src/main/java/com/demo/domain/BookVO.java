package com.demo.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	
	private Long bno;
	
	private String isbn;
	private String title;
	private String thumbnail;
	private String publisher;
	private String url;
	
	private Date datetime;
	private Date createdate;
	
	private List<AuthorVO> authors;
}
