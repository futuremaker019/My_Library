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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.domain.Member;
import com.demo.dto.MemberRequestDto;
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
	
	@GetMapping("/member/signup")
	public String getSignupPage(@RequestParam(required=false) String type, Model model) {
		model.addAttribute("type", type);
		return "/member/signup";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/member/info")
	public String getMyInfoPage(Model model, Authentication authentication) {
		Member member = memberService.getMember(authentication);
		model.addAttribute("member", member);
		
		return "/member/myinfo";
	}
	
	@PostMapping("/member/signup")
	public String signup(MemberRequestDto memberDto) {
		memberService.saveMemberInfo(memberDto);
		return "redirect:/member/login";
	}
	
	@ResponseBody
	@PostMapping(value="/member/verification/userid",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> checkIdDuplication(String userId) {
		Boolean hasUserId = memberService.verifyUserId(userId);
		if(hasUserId) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
	
	@ResponseBody
	@PostMapping(value="/member/verification/email",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> checkEmailDuplication(String email) {
		Boolean hasEmail = memberService.verifyEmail(email);
		if(hasEmail) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
	
	@ResponseBody
	@PostMapping(value="/member/verification/password",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> checkPasswordExisted(String password, Authentication authentication) {
		Boolean hasPassword = memberService.verifyPassword(password, authentication);
		if(hasPassword) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
	
	@ResponseBody
	@PostMapping(value="/member/password",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> changePassword(String changedPassword, Authentication authentication){
		Boolean isPasswordChanged = memberService.changePassword(changedPassword, authentication);
		if(isPasswordChanged) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
	
	@ResponseBody
	@PostMapping(value="/member/email",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Boolean> changeEmail (String changedEmail, Authentication authentication) {
		Boolean isEmailChanged = memberService.changeEmail(changedEmail, authentication);
		if(isEmailChanged) {
			return ResponseEntity.ok().body(true);
		}
		return ResponseEntity.ok().body(false);
	}
}
