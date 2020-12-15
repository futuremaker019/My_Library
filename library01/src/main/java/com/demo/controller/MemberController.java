package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.dto.MemberDto;
import com.demo.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/customLogin")
	public void login(String error, Model model) {
		log.info("login page loading done.");
		
		if(error != null) {
			model.addAttribute("error", "Please check your account again");
		}
	}
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customLogout")
	public void logout() {
		log.info("custom logout");
	}
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "/member/signup";
	}
	
	@PostMapping("/signup")
	public String signup(MemberDto memberDto) {
		memberService.saveMemberInfo(memberDto);
		return "redirect:/customLogin";
	}
}