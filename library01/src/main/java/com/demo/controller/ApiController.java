package com.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.service.ApiService;
import com.demo.service.AuthorService;
import com.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Setter(onMethod_ = @Autowired)
	private ApiService apiService;
	
	@PostMapping(value="/addbook", 
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> addBook(@RequestBody BookVO bookVO) {
		List<AuthorVO> authors = bookVO.getAuthors();
		apiService.register(bookVO, authors);
		
		try {
			return new ResponseEntity<>("Book added successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

//	@PostMapping(value="/addbook",
//			consumes = "application/json",
//			produces = {MediaType.TEXT_PLAIN_VALUE})
//	public ResponseEntity<String> addBook(
//				@RequestBody BookVO bookVO) {
//		
//		log.info("BookVO : " + bookVO);
//		
//		bookService.register(bookVO);
//		
//		try {
//			return new ResponseEntity<>("Book added successfully", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@PostMapping(value="/addauthors/",
//			consumes = "application/json",
//			produces = {MediaType.TEXT_PLAIN_VALUE})
//	public ResponseEntity<String> addAuthor(
//				@RequestBody List<AuthorVO> authors) {
//		
//		log.info("authors : " + authors);
//		
//		authorService.register(authors);
//		
//		try {
//			return new ResponseEntity<>("Author added successfully", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
