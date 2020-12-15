package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.domain.PageDTO;
import com.demo.domain.ReviewVO;
import com.demo.service.BookService;
import com.demo.service.ReviewService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookService;
	
	@Setter(onMethod_ = @Autowired)
	private ReviewService reviewService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String collections(Model model, Criteria criteria) {
		List<BookVO> bookList = bookService.getListWithPaging(criteria);
		log.info("book's List : " + bookList);
		
		int total = bookService.getTotal(criteria);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		return "/book/collection";
	}
	
	@GetMapping("/result")
	public String result(Model model, Criteria criteria) {
		List<BookVO> searchBookList = bookService.getSearchListWithPaging(criteria);
		log.info("book's List : " + searchBookList);
		
		int searchTotal = bookService.getTotalSearchItem(criteria);
		
		model.addAttribute("searchBookList", searchBookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, searchTotal));
		
		return "/book/result";
	}
	
	@GetMapping("/search")
	public String search() {
		return "/book/search";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/editing")
	public void editing(Model model, Criteria criteria) {
		List<BookVO> bookList = bookService.getListWithPaging(criteria);
		log.info("book's List : " + bookList);
		
		int total = bookService.getTotal(criteria);
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/one")
	public String bookDetails(Model model, @RequestParam("bno") Long bno) {
		BookVO book = bookService.getBook(bno);
		log.info("book details: " + book);
		
		List<AuthorVO> authors = book.getAuthors();
		log.info("author's object: " + authors);
		
		log.info("~~~~~~~~~~~~~~~bno : " + bno);
		ReviewVO review = reviewService.getReview(bno);
		log.info("review's object : " + review);
		
		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("review", review);
		
		return "/book/one";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/remove")
	public String removeBooks(@RequestParam(required = false) List<Long> bnos,
							@RequestParam(required = false) Long bno, RedirectAttributes rttr) {
		
		if(bnos != null) {
			bookService.removeBooks(bnos);
			return "redirect:/book";
		}
		
		bookService.remove(bno);
		rttr.addFlashAttribute("result", "Book remove successfully");
		return "redirect:/book";
	}
}
