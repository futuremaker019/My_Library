package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Criteria;
import com.demo.domain.Sentence;
import com.demo.dto.SentenceRequestDto;
import com.demo.dto.SentenceResponseDto;
import com.demo.service.SentenceService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class SentenceController {

	@Autowired
	private SentenceService sentenceService;
	
	@GetMapping(value="/books/{bno}/sentences",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<SentenceResponseDto>> getSentences(@PathVariable("bno") Long bno) {
		return ResponseEntity.ok().body(sentenceService.getSentences(bno));
	}
	
	@GetMapping(value="/sentences/{sentence_id}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<SentenceResponseDto> getSentence(@PathVariable("sentence_id") Long sentence_id){
		return new ResponseEntity<>(sentenceService.getSentence(sentence_id), HttpStatus.OK);
	}
	
	@PostMapping(value="/sentence",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<SentenceResponseDto> create(@RequestBody SentenceRequestDto sentenceReqeustDto){
		SentenceResponseDto sentenceResponseDto = sentenceService.addSentence(sentenceReqeustDto);
		
		try {
			return ResponseEntity.ok().body(sentenceResponseDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/sentences/{sentence_id}",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<SentenceResponseDto> modify(@RequestBody SentenceRequestDto sentenceRequestDto, 
							@PathVariable("sentence_id")Long sentence_id){
		SentenceResponseDto sentenceResponseDto = sentenceService.modifySentence(sentenceRequestDto); 
		
		try {
			return ResponseEntity.ok().body(sentenceResponseDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		} 
	}
	
	@DeleteMapping(value="/sentences/{sentence_id}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("sentence_id") Long sentence_id){
		return sentenceService.removeSentence(sentence_id) == 1 
				? new ResponseEntity<>("Sentence deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
