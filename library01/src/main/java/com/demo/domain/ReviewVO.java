package com.demo.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class ReviewVO {

	private Long review_id;
	private Long bno;
	
	private String content;
	private int rating;
	
	private Date createdate;
	private Date modifieddate;
}
