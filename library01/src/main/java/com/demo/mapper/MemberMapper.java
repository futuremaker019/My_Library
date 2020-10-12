package com.demo.mapper;

import com.demo.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
}
