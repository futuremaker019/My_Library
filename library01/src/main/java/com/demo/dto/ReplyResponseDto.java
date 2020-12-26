package com.demo.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyResponseDto {

	private Long reply_id;
	private String reply;
	private String replyer;
	private Date createddate;
}
