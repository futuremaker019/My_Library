package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Reply;
import com.demo.dto.ReplyRequestDto;
import com.demo.dto.ReplyResponseDto;
import com.demo.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;

	@GetMapping("/replies/{reply_id}")
	public ResponseEntity<String> getReply(@PathVariable("reply_id") Long reply_id) {
		
		return ResponseEntity.ok().body("replies brought done.");
	}
	
	@GetMapping(value = "/board/{board_id}/replies/page/{page}",
			produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyResponseDto>> getReplies(@PathVariable("board_id") Long board_id,
											@PathVariable("page") Integer page) {
		List<ReplyResponseDto> replyResponseDtos = replyService.getReplies(board_id);
		return ResponseEntity.ok().body(replyResponseDtos);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/reply/creation", 
			consumes = "application/json",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyResponseDto> createReply(@RequestBody ReplyRequestDto replyRequestDto,
									Authentication authentication) {
		log.info("replyRequestDto : " + replyRequestDto);
		
		ReplyResponseDto replyResponseDto = replyService.addReply(replyRequestDto, authentication);
		
		return ResponseEntity.ok().body(replyResponseDto);
	}
	
	@DeleteMapping("/{reply_id}")
	public ResponseEntity<String> deleteReply(@PathVariable("board_id") Long board_id) {
		
		return ResponseEntity.ok().body("reply deleted done.");
	}
}