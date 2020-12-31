package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.domain.ReviewVO;
import com.demo.dto.BookDeleteDto;
import com.demo.dto.PageDTO;
import com.demo.service.BookService;
import com.demo.service.ReviewService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ReviewService reviewService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String collections(Model model, Criteria criteria, Authentication authentication) {
		List<BookVO> bookList = bookService.getListWithPaging(criteria, authentication);
		
		int total = bookService.getTotal(criteria, authentication);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/books/collection";
	}
	
	@GetMapping("/result")
	public String result(Model model, Criteria criteria) {
		List<BookVO> searchBookList = bookService.getSearchListWithPaging(criteria);
		
		int searchTotal = bookService.getTotalSearchItem(criteria);
		
		model.addAttribute("searchBookList", searchBookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, searchTotal));
		
		return "/books/result";
	}
	
	@GetMapping("/search")
	public String search(Authentication authentication, Model model) {
		model.addAttribute("authentication", authentication);
		
		return "/books/search";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/editing")
	public void editing(Model model, Criteria criteria, Authentication authentication) {
		List<BookVO> bookList = bookService.getListWithPaging(criteria, authentication);
		List<AuthorVO> authors = null;
		
		for (BookVO book : bookList) {
			authors = book.getAuthors();
		}
		
		int total = bookService.getTotal(criteria, authentication);
		
		model.addAttribute("authors", authors);
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{bno}")
	public String bookDetails(Model model, @PathVariable("bno") Long bno) {
		BookVO book = bookService.getBook(bno);
		List<AuthorVO> authors = book.getAuthors();
		
		ReviewVO review = reviewService.getReview(bno);
		
		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("review", review);
		
		return "/books/book";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/remove")
	public String removeBooks(@RequestParam Long bno, RedirectAttributes rttr) {
		
		bookService.remove(bno);
		rttr.addFlashAttribute("result", "Book remove successfully");
		return "redirect:/books";
	}
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<String> removeBookInEdit(@PathVariable("bno") Long bno) {
		bookService.remove(bno);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/edit",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE}) 
	public ResponseEntity<String> removeBooksInEdit(@RequestBody BookDeleteDto bookDeleteDto) {
		bookService.removeBooks(bookDeleteDto.getBnos());
		
		return ResponseEntity.ok().build();
	}
}
