package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Review;
import com.demo.dto.ReviewRequestDto;
import com.demo.dto.ReviewResponseDto;
import com.demo.service.ReviewService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Setter(onMethod_ = @Autowired)
	private ReviewService reviewService;
	
	@GetMapping(value="/{book_id}")
	public ResponseEntity<Review> get(@PathVariable("book_id") Long book_id) {
		return new ResponseEntity<>(reviewService.getReview(book_id), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="",
			consumes = "application/json",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReviewResponseDto> create(@RequestBody ReviewRequestDto reviewVO) {
		ReviewResponseDto reviewResponseDto = reviewService.register(reviewVO);
		
		return ResponseEntity.ok().body(reviewResponseDto);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping(value="/{book_id}", 
			consumes = "application/json; charset=utf-8",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReviewResponseDto> modify(@RequestBody ReviewRequestDto reviewRequestDto, 
										 @PathVariable("book_id") Long book_id) {
		
		ReviewResponseDto reviewResponseDto = reviewService.modify(reviewRequestDto);
		try {
			return ResponseEntity.ok().body(reviewResponseDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value="/{book_id}")
	public ResponseEntity<String> remove(@PathVariable("book_id") Long book_id) {
		return reviewService.delete(book_id) == 1 
				? new ResponseEntity<>("Deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
