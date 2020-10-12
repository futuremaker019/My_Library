package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.service.ApiService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Setter(onMethod_ = @Autowired)
	private ApiService apiService;
	
	@PreAuthorize("isAuthenticated()")
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
	
	@GetMapping(value="/{isbn}")
	public ResponseEntity<BookVO> getBook(@PathVariable("isbn") String isbn) {
		return new ResponseEntity<>(apiService.findBookByIsbn(isbn), HttpStatus.OK);
	}
}
