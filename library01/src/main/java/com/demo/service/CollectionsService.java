package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Collection;
import com.demo.dto.CollectionRequestDto;
import com.demo.dto.CollectionResponseDto;
import com.demo.mapper.CollectionMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionsService{
	
	private final CollectionMapper collectionMapper;
	
	public List<CollectionResponseDto> getCollections(Long book_id) {
		return collectionMapper.findAllByBookId(book_id).stream()
				.map(collection -> CollectionResponseDto.builder()
						.collection_id(collection.getCollection_id())
						.content(collection.getContent())
						.modifieddate(collection.getModifieddate())
						.build())
				.collect(Collectors.toList());
	}
	
	public CollectionResponseDto getCollection(Long sentence_id) {
		Collection findSentence = collectionMapper.findById(sentence_id);
		
		CollectionResponseDto sentenceResponseDto = CollectionResponseDto.builder()
				.collection_id(findSentence.getCollection_id())
				.content(findSentence.getContent())
				.modifieddate(findSentence.getModifieddate())
				.build();
		
		return sentenceResponseDto;
	}

	public CollectionResponseDto addCollection(CollectionRequestDto sentenceRequestDto) {
		Collection collection = Collection.builder()
				.book_id(sentenceRequestDto.getBook_id())
				.content(sentenceRequestDto.getContent())
				.build();
		
		CollectionResponseDto collectionResponseDto = null;
		if(collectionMapper.save(collection)) {
			Collection findCollection = collectionMapper.findById(collection.getCollection_id());
			collectionResponseDto = CollectionResponseDto.builder()
					.collection_id(findCollection.getCollection_id())
					.content(findCollection.getContent())
					.modifieddate(findCollection.getModifieddate())
					.build();
		}
		
		return collectionResponseDto;
	}

	public CollectionResponseDto modifyCollection(CollectionRequestDto collectionRequestDto) {
		Collection collection = Collection.builder()
				.collection_id(collectionRequestDto.getCollection_id())
				.content(collectionRequestDto.getContent())
				.build();
		
		CollectionResponseDto collectionResponseDto = null;
		if(collectionMapper.update(collection)) {
			Collection findCollection = collectionMapper.findById(collection.getCollection_id());
			collectionResponseDto = CollectionResponseDto.builder()
					.collection_id(findCollection.getCollection_id())
					.content(findCollection.getContent())
					.modifieddate(findCollection.getModifieddate())
					.build();
		}
		
		return collectionResponseDto;
	}
	
	public int removeCollection(Long collection_id) {
		return collectionMapper.deleteById(collection_id);
	}
}
