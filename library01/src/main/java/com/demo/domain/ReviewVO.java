package com.demo.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewVO {

	private Long bno;
	private Long rno;
	
	private String metion;
	private Character rating;
	
	private Date regdate;
	private Date updatedate;
	
	@Builder
	public ReviewVO(Long bno, Long rno, String metion, Character rating, Date regdate, Date updatedate) {
		this.bno = bno;
		this.rno = rno;
		this.metion = metion;
		this.rating = rating;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
}
