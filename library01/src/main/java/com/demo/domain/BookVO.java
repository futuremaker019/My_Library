package com.demo.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
	
	private Long book_id;
	private Long member_id;
	
	private String isbn;
	private String title;
	private String thumbnail;
	private String publisher;
	private String url;
	private String userId;
	
	private Date datetime;
	private Date createdate;
	
	private List<AuthorVO> authors;
	
	public void saveMemberInfo(Long member_id, String userId) {
		this.member_id = member_id;
		this.userId = userId;
	}
}
