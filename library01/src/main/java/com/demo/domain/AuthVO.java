package com.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthVO {

	private String userId;
	private String auth;
	
	@Builder
	public AuthVO(String userId, String auth) {
		this.userId = userId;
		this.auth = auth;
	}
}
