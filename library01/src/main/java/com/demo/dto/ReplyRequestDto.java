package com.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReplyRequestDto {

	private Long board_id;
	private String reply;
}
