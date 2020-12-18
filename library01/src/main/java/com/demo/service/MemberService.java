package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.AuthVO;
import com.demo.domain.MemberVO;
import com.demo.domain.Role;
import com.demo.dto.MemberDto;
import com.demo.mapper.AuthMapper;
import com.demo.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private AuthMapper authMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void saveMemberInfo(MemberDto memberDto) {
		MemberVO member = MemberVO.builder()
				.userId(memberDto.getUserId())
				.userPw(passwordEncoder.encode(memberDto.getUserPw()))
				.email(memberDto.getEmail())
				.build();
		
		List<AuthVO> authList = new ArrayList<>(); 
		authList.add(new AuthVO(memberDto.getUserId(), Role.MEMBER.getValue()));
		
		if(memberMapper.insert(member) == 1) {
			for (AuthVO authVO : authList) {
				authMapper.insert(authVO);
			}
		}
	}
}
