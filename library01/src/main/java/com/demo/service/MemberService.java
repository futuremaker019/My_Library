package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Auth;
import com.demo.domain.Member;
import com.demo.domain.Role;
import com.demo.dto.MemberRequestDto;
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
	
	public void saveMemberInfo(MemberRequestDto memberRequestDto) {
		List<Auth> authList = new ArrayList<>();
		
		Member member = Member.builder()
				.userId(memberRequestDto.getUserId())
				.password(passwordEncoder.encode(memberRequestDto.getPassword()))
				.email(memberRequestDto.getEmail())
				.build();
		
		if(memberMapper.insert(member)) {
			if(memberRequestDto.getType().equals("1")) {
				authList.add(new Auth(member.getMember_id(), Role.ADMIN.getValue()));
			}
			
			authList.add(new Auth(member.getMember_id(), Role.MEMBER.getValue()));
			for (Auth authVO : authList) {
				authMapper.insert(authVO);
			}
		}
	}
	
	public Boolean verifyUserId(String userId) {
		Member member = memberMapper.findByUserId(userId);
		
		if(member != null) {
			if (member.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean verifyEmail(String email) {
		Member member = memberMapper.findByEmail(email);
		if(member != null) {
			if (member.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean verifyPassword(String password, Authentication authentication) {
		String userId = getUserId(authentication);
		
		Member member = memberMapper.findByUserId(userId);
		if(passwordEncoder.matches(password, member.getPassword())) {
			return true;
		}
		return false;
	}
	
	public Boolean changePassword(String password, Authentication authentication) {
		String userId = getUserId(authentication);
		
		Member member = memberMapper.findByUserId(userId);
		member.changePassword(passwordEncoder.encode(password));
		
		if(memberMapper.modifyPassword(member)) {
			return true;
		}
		return false;
	}
	
	public Boolean changeEmail(String email, Authentication authentication) {
		String userId = getUserId(authentication);
		
		Member member = memberMapper.findByUserId(userId);
		member.changeEmail(email);
		
		if(memberMapper.modifyEmail(member)) {
			return true;
		}
		return false;
	}
	
	public Member getMember(Authentication authentication) {
		String userId = getUserId(authentication);
		
		return memberMapper.findByUserId(userId);
	}

	private String getUserId(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return userDetails.getUsername();
		 
	}
}
