package com.demo.mapper;

import com.demo.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
	
	public int insert(MemberVO member);
	
	public MemberVO findByUserId(String userId);
	
	public MemberVO findByEmail(String email);
}
