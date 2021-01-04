package com.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.domain.BookVO;
import com.demo.dto.BoardResponseDto;
import com.demo.service.BoardService;
import com.demo.service.BookService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home(Locale locale, Model model, Authentication authentication) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		List<BookVO> images = bookService.getBooksImage(authentication);
		List<BoardResponseDto> posts = boardService.getPostsTop4();
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("images", images);
		model.addAttribute("posts", posts);
		
		return "home";
	}
	
}
