package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ReplyRequestDto;
import com.demo.dto.ReplyResponseDto;
import com.demo.service.ReplyService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;

	@GetMapping("/reply/{reply_id}")
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
	
	@PostMapping("/creation")
	public ResponseEntity<String> createReply(@RequestBody ReplyRequestDto replyRequestDto) {
		log.info("replyRequestDto : " + replyRequestDto);
		
		return ResponseEntity.ok().body("replied done.");
	}
	
	@DeleteMapping("/{reply_id}")
	public ResponseEntity<String> deleteReply(@PathVariable("board_id") Long board_id) {
		
		return ResponseEntity.ok().body("reply deleted done.");
	}
}
