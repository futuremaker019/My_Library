package com.demo.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.domain.Book;
import com.demo.dto.BoardResponseDto;
import com.demo.service.BoardService;
import com.demo.service.BookService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String home(Locale locale, Model model, Authentication authentication) {
		
		List<Book> images = bookService.getBooksImage(authentication);
		List<BoardResponseDto> posts = boardService.getPostsTop4();
		
		model.addAttribute("images", images);
		model.addAttribute("posts", posts);
		
		return "home";
	}
	
}
