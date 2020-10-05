package com.demo.domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentenceVO {

	private Long bno;
	private Long sno;
	private String sentence;
	private Date createDate;
	private Date modifiedDate;
	
	@Builder
	public SentenceVO(Long bno, Long sno, String sentence, Date createDate, Date modifiedDate) {
		this.bno = bno;
		this.sno = sno;
		this.sentence = sentence;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
}
