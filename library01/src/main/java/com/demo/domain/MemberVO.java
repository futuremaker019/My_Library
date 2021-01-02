package com.demo.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberVO {

	private String userId;
	private String userPw;
	private String email;
	private boolean enabled;
	
	private Date createdDate;
	private Date updatedDate;
	
	private List<AuthVO> authList;
	
	public void changePassword(String userPw) {
		this.userPw = userPw;
	}
	
	public void changeEmail(String email) {
		this.email = email;
	}
}
