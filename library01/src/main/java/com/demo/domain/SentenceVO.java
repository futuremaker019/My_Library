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
public class SentenceVO {

	private Long bno;
	private Long sno;
	private String sentence;
	private Date createdate;
	private Date modifieddate;
	
	@Builder
	public SentenceVO(Long bno, Long sno, String sentence, Date createdate, Date modifieddate) {
		this.bno = bno;
		this.sno = sno;
		this.sentence = sentence;
		this.createdate = createdate;
		this.modifieddate = modifieddate;
	}
}
