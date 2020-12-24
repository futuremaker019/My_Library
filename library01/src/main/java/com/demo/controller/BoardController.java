package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Criteria;
import com.demo.dto.AttachmentDto;
import com.demo.dto.BoardDto;
import com.demo.dto.PageDTO;
import com.demo.service.AttachmentService;
import com.demo.service.BoardService;
import com.demo.service.UploadService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	
	@Autowired
	private AttachmentService attachmentService; 
	
	@GetMapping("/list")
	public String getList(Model model, Criteria criteria) {
		model.addAttribute("boardList", boardService.getPostListWithPaging(criteria));
		
		int total = boardService.getTotalPost();
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/board/list";
	}
	
	@GetMapping("/post/{id}")
	public String getPage(@PathVariable("id") Long id, Model model, 
							@ModelAttribute("criteria") Criteria criteria) {
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
	
	@GetMapping("/modify/{board_id}")
	public String getPostModifyPage(@PathVariable("board_id") Long board_id, 
									Model model, @ModelAttribute("criteria") Criteria criteria) {
		model.addAttribute("post", boardService.getPostPage(board_id));
		model.addAttribute("attachments", attachmentService.getAttachments(board_id));
		
		return "/board/modify";
	}
	
	
	@PostMapping("/modify/{board_id}")
	public String modifyPost(@PathVariable("board_id") Long board_id, BoardDto boardDto, 
							@ModelAttribute("criteria") Criteria criteria) {
		List<AttachmentDto> attachmentDtos = boardDto.getAttachments();
		boardService.modifyPost(board_id, boardDto, attachmentDtos);
		
		return "redirect:/board/list";
	}
}
