package com.demo.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewVO {

	private Long rno;
	private Long bno;
	
	private String mention;
	private Integer rating;
	
	private Date createdate;
	private Date modifieddate;
	
	@Builder
	public ReviewVO(Long bno, Long rno, String mention, Integer rating, Date createdate, Date modifieddate) {
		this.bno = bno;
		this.rno = rno;
		this.mention = mention;
		this.rating = rating;
		this.createdate = createdate;
		this.modifieddate = modifieddate;
	}
}
