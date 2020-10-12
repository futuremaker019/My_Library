package com.demo.domain;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;
	
	private Date createdDate;
	private Date updatedDate;
	
	private List<AuthVO> authList;

	@Builder
	public MemberVO(String userId, String userPw, String userName, boolean enabled, Date createdDate, Date updatedDate,
			List<AuthVO> authList) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.enabled = enabled;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.authList = authList;
	}
}
