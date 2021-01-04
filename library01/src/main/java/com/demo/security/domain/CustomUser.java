package com.demo.security.domain;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.demo.domain.MemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User{
	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	public CustomUser(MemberVO member) {
		super(member.getUserId(), member.getPassword(), member.getRoles().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getRole()))
				.collect(Collectors.toList()));
		
		this.member = member;
	}
}
