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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.ReviewVO;
import com.demo.service.ReviewService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/review")
public class ReviewController {

	@Setter(onMethod_ = @Autowired)
	private ReviewService reviewService;
	
	@GetMapping(value="/{bno}")
	public ResponseEntity<ReviewVO> get(@PathVariable("bno") Long bno) {
		
		log.info("get bno : " + bno);
		
		return new ResponseEntity<>(reviewService.getReview(bno), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReviewVO reviewVO) {
		
		log.info(reviewVO);
		int insertCount  = reviewService.register(reviewVO);
		
		return insertCount == 1 
				? new ResponseEntity<>("review added successfully.", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value="/admin/{bno}", 
			consumes = "application/json; charset=utf-8",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReviewVO review, 
										 @PathVariable("bno")Long bno) {
		log.info("~~~~~~~~~ putmapping review RequestBody : " + review);
		
		log.info("modify bno : " + bno);
		
		return reviewService.modify(review) == 1
				? new ResponseEntity<>("Modified successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value="/admin/{bno}")
	public ResponseEntity<String> remove(@PathVariable("bno")Long bno) {
		
		log.info("modify bno : " + bno);
		
		return reviewService.delete(bno) == 1 
				? new ResponseEntity<>("Deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
