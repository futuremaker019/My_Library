package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dto.BoardDto;
import com.demo.service.BoardService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService; 
	
	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("boardList", boardService.getPostList());
		
		return "/board/list";
	}
	
	@GetMapping("/post/{id}")
	public String getPage(@PathVariable("id") Long id, Model model) {
		model.addAttribute("post",boardService.getPostPage(id));
		
		return "/board/post";
	}
	
	@GetMapping("/create")
	public String getCreatePage() {
		return "/board/create";
	}
	
	@PostMapping("/create")
	public String create(BoardDto boardDto, RedirectAttributes rttr, Authentication authentication) {
		boardService.addPost(boardDto, authentication);
		rttr.addFlashAttribute("Post added successfully");
		
		return "redirect:/board/list";
	}
}
