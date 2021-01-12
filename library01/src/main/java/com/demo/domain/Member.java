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
public class Member {

	private Long member_id;
	
	private String userId;
	private String password;
	private String email;
	private boolean enabled;
	
	private Date createdDate;
	private Date updatedDate;
	
	private List<Auth> roles;
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	public void changeEmail(String email) {
		this.email = email;
	}
}
