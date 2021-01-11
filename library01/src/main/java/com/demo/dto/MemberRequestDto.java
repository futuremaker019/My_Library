package com.demo.dto;

import lombok.Data;

@Data
public class MemberDto {
	
	private Long member_id;
	
	private String userId;
	private String password;
	private String email;
	
}
