package com.demo.dto;

import java.sql.Date;
import java.util.List;

import com.demo.domain.AuthVO;
import com.demo.domain.MemberVO;

import lombok.Data;

@Data
public class MemberDto {
	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;
	
	private Date createdDate;
	private Date updatedDate;
	
	private List<AuthVO> authList;
	
	public MemberVO toEntity() {
		return MemberVO.builder()
				.build();
	}
}
