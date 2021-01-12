package com.demo.dto;

import lombok.Data;

@Data
public class MemberRequestDto {
	
	private Long member_id;
	
	private String userId;
	private String password;
	private String email;
	
	// admin 가입시 type 1로 받아 회원가입을 진행시킨다.
	private String type;
}
