package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.AuthVO;
import com.demo.domain.MemberVO;
import com.demo.domain.Role;
import com.demo.dto.MemberDto;
import com.demo.mapper.AuthMapper;
import com.demo.mapper.MemberMapper;

import lombok.Setter;

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
		List<AuthVO> authList = new ArrayList<>();
		
		MemberVO member = MemberVO.builder()
				.userId(memberDto.getUserId())
				.password(passwordEncoder.encode(memberDto.getPassword()))
				.email(memberDto.getEmail())
				.build();
		
		if(memberMapper.insert(member) == 1) {
			authList.add(new AuthVO(member.getMember_id(), Role.MEMBER.getValue()));
			for (AuthVO authVO : authList) {
				authMapper.insert(authVO);
			}
		}
	}
	
	public Boolean verifyUserId(String userId) {
		MemberVO member = memberMapper.findByUserId(userId);
		
		if(member != null) {
			if (member.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean verifyEmail(String email) {
		MemberVO member = memberMapper.findByEmail(email);
		if(member != null) {
			if (member.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean verifyPassword(String password, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		MemberVO member = memberMapper.findByUserId(userId);
		if(passwordEncoder.matches(password, member.getPassword())) {
			return true;
		}
		return false;
	}
	
	public Boolean changePassword(String password, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		MemberVO member = memberMapper.findByUserId(userId);
		member.changePassword(passwordEncoder.encode(password));
		
		if(memberMapper.modifyPassword(member)) {
			return true;
		}
		return false;
	}
	
	public Boolean changeEmail(String email, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		MemberVO member = memberMapper.findByUserId(userId);
		member.changeEmail(email);
		
		if(memberMapper.modifyEmail(member)) {
			return true;
		}
		return false;
	}
	
	public MemberVO getMember(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		return memberMapper.findByUserId(userId);
	}
}
