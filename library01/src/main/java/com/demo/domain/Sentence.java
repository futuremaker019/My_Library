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
public class Sentence {

	private Long bno;
	private Long sentence_id;
	private String content;
	private Date createdate;
	private Date modifieddate;
	
}
