package com.demo.mapper;

import com.demo.domain.Member;

public interface MemberMapper {
	public Member read(String userId);
	
	public boolean insert(Member member);
	
	public Member findByUserId(String userId);
	
	public Member findByEmail(String email);
	
	public boolean modifyPassword(Member member);
	
	public boolean modifyEmail(Member member);
}
