package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.domain.PageDTO;
import com.demo.service.BookService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookService;
	
	@GetMapping("")
	public String collections(Model model, Criteria criteria) {
		List<BookVO> bookList = bookService.getListWithPaging(criteria);
		log.info("book's List : " + bookList);
		
		int total = bookService.getTotal(criteria);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/book/collection";
	}
	
	@GetMapping("/search")
	public String search() {
		return "/book/search";
	}
}
