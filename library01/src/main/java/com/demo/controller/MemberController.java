package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.dto.MemberDto;
import com.demo.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/member/login")
	public void login(String error, Model model) {
		if(error != null) {
			model.addAttribute("error", "Please check your account again");
		}
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/member/logout")
	public void logout() {
		log.info("custom logout");
	}
	
	@GetMapping("/member/signup")
	public String getSignupPage() {
		return "/member/signup";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/info")
	public String getMyInfoPage() {
		return "/member/myinfo";
	}
	
	@PostMapping("/member/signup")
	public String signup(MemberDto memberDto) {
		memberService.saveMemberInfo(memberDto);
		return "redirect:/member/login";
	}
	
	@ResponseBody
	@PostMapping(value="/member/identification",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> checkIdDuplication(String userId) {
		Boolean isUserExisted = memberService.verifyUserId(userId);
		if(isUserExisted) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
	
	@ResponseBody
	@PostMapping(value="/member/verification/email",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> checkEmailDuplication(String email) {
		Boolean isEmailExisted = memberService.verifyEmail(email);
		if(isEmailExisted) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
}
