package com.demo.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
	
	private Long reply_id;
	private String reply;
	private String replier;
	private Date createddate;
	private Date updateddate;
	
	private Long board_id;
}
