package com.demo.controller;

import java.util.List;

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

import com.demo.domain.Criteria;
import com.demo.domain.SentenceVO;
import com.demo.service.SentenceService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sentence")
public class SentenceController {

	@Setter(onMethod_ = @Autowired)
	private SentenceService sentenceService;
	
	@GetMapping(value="/{bno}/{page}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<SentenceVO>> getList(
							@PathVariable("bno")Long bno,
							@PathVariable("page")int page) {
		
		log.info("------------------------------get List ------------------------------");
		Criteria criteria = new Criteria(page, 5);
		log.info("Criteria : " + criteria);
		List<SentenceVO> sentences = sentenceService.getListWithPaging(criteria, bno);
		
		return new ResponseEntity<>(sentences, HttpStatus.OK);
	}
	
	@GetMapping(value="/{sno}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<SentenceVO> getOne(@PathVariable("sno")Long sno){
		
		log.info("getOne sentence sno: " + sno );
		return new ResponseEntity<>(sentenceService.getOne(sno), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MEMBER, ROLE_ADMIN')")
	@PostMapping(value="/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody SentenceVO sentence){
		
		log.info("This is sentenceVO from client : " + sentence);
		int insertSentence = sentenceService.register(sentence);
		log.info("This is insertSentence :" + insertSentence);
		
		return insertSentence == 1 
				? new ResponseEntity<>("sentence added successfully.", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value="/{sno}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("sno")Long sno){
		
		log.info("remove sentence sno : " + sno );
		return sentenceService.remove(sno) == 1 
				? new ResponseEntity<>("Sentence deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/{sno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody SentenceVO sentenceVO, 
							@PathVariable("sno")Long sno){
		
		log.info("This is sentencVO from client : " + sentenceVO);
		log.info("sentenceVO's sno : " + sentenceVO.getSno());
		log.info("sentenceVO's sentence : " + sentenceVO.getSentence());
		
		return sentenceService.modify(sentenceVO) == 1 
				? new ResponseEntity<>("Sentence modified successfully.", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
