package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Author;
import com.demo.domain.Book;
import com.demo.domain.Criteria;
import com.demo.domain.Review;
import com.demo.dto.BookRequestDto;
import com.demo.dto.PageDTO;
import com.demo.service.BookService;
import com.demo.service.ReviewService;

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
		List<Book> bookList = bookService.getListWithPaging(criteria, authentication);
		
		int total = bookService.getTotal(criteria, authentication);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/books/collection";
	}
	
	@GetMapping("/result")
	public String result(Model model, Criteria criteria) {
		List<Book> searchBookList = bookService.getSearchListWithPaging(criteria);
		
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
	@GetMapping("/edit")
	public String editing(Model model, Criteria criteria, Authentication authentication) {
		List<Book> bookList = bookService.getListWithPaging(criteria, authentication);
		
		int total = bookService.getTotal(criteria, authentication);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/books/editing";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{book_id}")
	public String bookDetails(Model model, @PathVariable("book_id") Long book_id, Authentication authentication) {
		Book book = bookService.getBook(book_id);
		List<Author> authors = book.getAuthors();
		
		Review review = reviewService.getReview(book_id);
		
		model.addAttribute("authentication", authentication);
		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("review", review);
		
		return "/books/book";
	}
	
	@ResponseBody
	@PostMapping(value="/verification",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Boolean> getBook(@RequestBody BookRequestDto bookRequestDto) {
		Boolean hasBook = bookService.verifyExistedBook(bookRequestDto);
		if(hasBook) {
			return ResponseEntity.ok().body(true); 
		}
		return ResponseEntity.ok().body(false);
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PostMapping(value="", 
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> addBook(@RequestBody BookRequestDto bookRequestDto, Authentication authentication) {
		List<Author> authors = bookRequestDto.getAuthors();
		bookService.register(bookRequestDto, authors, authentication);
		
		try {
			return new ResponseEntity<>("Book added in your library.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/remove")
	public String removeBooks(@RequestParam Long book_id, RedirectAttributes rttr) {
		
		bookService.remove(book_id);
		rttr.addFlashAttribute("result", "Book remove successfully");
		
		return "redirect:/books";
	}
	
	@DeleteMapping("/{book_id}")
	public ResponseEntity<String> removeBookInEdit(@PathVariable("book_id") Long book_id) {
		bookService.remove(book_id);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/edit",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}) 
	public ResponseEntity<List<Long>> removeBooksInEdit(@RequestBody BookRequestDto bookReqeustDto) {
		bookService.removeBooks(bookReqeustDto.getBook_ids());
		
		return ResponseEntity.ok().body(bookReqeustDto.getBook_ids());
	}
}
