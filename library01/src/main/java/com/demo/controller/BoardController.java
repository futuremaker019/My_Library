package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dto.AttachmentDto;
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
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String getCreatePage(Authentication authentication, Model model) {
		
		/* model.addAttribute("authentication", ) */
		
		return "/board/create";
	}
	
	
	@PostMapping("/posting") 
	public String create(BoardDto boardDto, Authentication authentication) {
		List<AttachmentDto> attechmentDtos = boardDto.getAttachments();
		boardService.addPost(boardDto, attechmentDtos, authentication);
	  
	return "redirect:/board/list"; 
  }
	 
}
