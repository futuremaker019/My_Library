package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class BoardLikeController {
	
	@PostMapping("/board/{board_id}/like")
	public ResponseEntity<String> addLike(@PathVariable Long board_id, String userId){
		log.info("userId : " + userId);
		log.info("board_id : " + board_id);
		
		return ResponseEntity.ok().body("like increased");
	}
	
	@DeleteMapping("/board/{board_id}/like")
	public ResponseEntity<String> deleteLike(@PathVariable Long board_id, String userId){
		
		return ResponseEntity.ok().body("like increased");
	}
}
