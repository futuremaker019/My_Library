package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.Criteria;
import com.demo.dto.AttachmentResponseDto;
import com.demo.dto.BoardDto;
import com.demo.dto.PageDTO;
import com.demo.service.AttachmentService;
import com.demo.service.BoardLikeService;
import com.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final BoardLikeService boardLikeService;
	private final AttachmentService attachmentService; 
	
	@GetMapping("")
	public String getList(Model model, Criteria criteria) {
		model.addAttribute("boardList", boardService.getPostListWithPaging(criteria));
		
		int total = boardService.getTotalPost();
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/board/list";
	}
	
	@GetMapping("/{board_id}")
	public String getPage(@PathVariable("board_id") Long board_id, Model model, 
							@ModelAttribute("criteria") Criteria criteria, Authentication authentication) {
		model.addAttribute("post",boardService.getPostPage(board_id));
		model.addAttribute("totalLikes", boardLikeService.getTotalLike(board_id));
		
		model.addAttribute("authentication", authentication);
		
		return "/board/post";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/post")
	public String getCreatePage(Authentication authentication, Model model) {
		
		return "/board/create";
	}
	
	@PostMapping("/post")
	public String create(BoardDto boardDto, Authentication authentication) {
		List<AttachmentResponseDto> attechmentDtos = boardDto.getAttachments();
		boardService.addPost(boardDto, attechmentDtos, authentication);
	  
	return "redirect:/board"; 
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
		List<AttachmentResponseDto> attachmentDtos = boardDto.getAttachments();
		boardService.modifyPost(board_id, boardDto, attachmentDtos);
		
		return "redirect:/board/"+ board_id + criteria.getListLink();
	}
	
	@PostMapping("/delete/{board_id}")
	public String deletePost(@PathVariable("board_id") Long board_id, @ModelAttribute("criteria") Criteria criteria) {
		boardService.removePostAndFiles(board_id);
		
		return "redirect:/board" + criteria.getListLink();
	}
}
