package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CollectionRequestDto;
import com.demo.dto.CollectionResponseDto;
import com.demo.service.CollectionsService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class CollectionsController {

	@Autowired
	private CollectionsService CollectionService;
	
	@GetMapping(value="/books/{book_id}/collections",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<CollectionResponseDto>> getCollections(@PathVariable("book_id") Long book_id) {
		return ResponseEntity.ok().body(CollectionService.getCollections(book_id));
	}
	
	@GetMapping(value="/collections/{collection_id}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CollectionResponseDto> getCollection(@PathVariable("collection_id") Long collection_id){
		return new ResponseEntity<>(CollectionService.getCollection(collection_id), HttpStatus.OK);
	}
	
	@PostMapping(value="/collection",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CollectionResponseDto> create(@RequestBody CollectionRequestDto collectionReqeustDto){
		CollectionResponseDto collectionResponseDto = CollectionService.addCollection(collectionReqeustDto);
		
		try {
			return ResponseEntity.ok().body(collectionResponseDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},
			value="/collections/{collection_id}",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CollectionResponseDto> modify(@RequestBody CollectionRequestDto collectionRequestDto, 
							@PathVariable("collection_id")Long collection_id){
		CollectionResponseDto collectionResponseDto = CollectionService.modifyCollection(collectionRequestDto); 
		
		try {
			return ResponseEntity.ok().body(collectionResponseDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		} 
	}
	
	@DeleteMapping(value="/collections/{collection_id}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("collection_id") Long collection_id){
		return CollectionService.removeCollection(collection_id) == 1 
				? new ResponseEntity<>("collection deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
