package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Sentence;
import com.demo.dto.SentenceRequestDto;
import com.demo.dto.SentenceResponseDto;
import com.demo.mapper.SentenceMapper;

@Service
@Transactional
public class SentenceService{
	
	@Autowired
	private SentenceMapper sentenceMapper;
	
	public List<SentenceResponseDto> getSentences(Long bno) {
		return sentenceMapper.findAllByBookId(bno).stream()
				.map(sentence -> SentenceResponseDto.builder()
						.sentence_id(sentence.getSentence_id())
						.content(sentence.getContent())
						.modifieddate(sentence.getModifieddate())
						.build())
				.collect(Collectors.toList());
	}
	
	public SentenceResponseDto getSentence(Long sentence_id) {
		Sentence findSentence = sentenceMapper.findById(sentence_id);
		
		SentenceResponseDto sentenceResponseDto = SentenceResponseDto.builder()
				.sentence_id(findSentence.getSentence_id())
				.content(findSentence.getContent())
				.modifieddate(findSentence.getModifieddate())
				.build();
		
		return sentenceResponseDto;
	}

	public SentenceResponseDto addSentence(SentenceRequestDto sentenceRequestDto) {
		Sentence sentence = Sentence.builder()
				.bno(sentenceRequestDto.getBno())
				.content(sentenceRequestDto.getContent())
				.build();
		
		SentenceResponseDto sentenceResponseDto = null;
		if(sentenceMapper.save(sentence)) {
			Sentence findSentence = sentenceMapper.findById(sentence.getSentence_id());
			sentenceResponseDto = SentenceResponseDto.builder()
					.sentence_id(findSentence.getSentence_id())
					.content(findSentence.getContent())
					.modifieddate(findSentence.getModifieddate())
					.build();
		}
		
		return sentenceResponseDto;
	}

	public SentenceResponseDto modifySentence(SentenceRequestDto sentenceRequestDto) {
		Sentence sentence = Sentence.builder()
				.sentence_id(sentenceRequestDto.getSentence_id())
				.content(sentenceRequestDto.getContent())
				.build();
		
		SentenceResponseDto sentenceResponseDto = null;
		if(sentenceMapper.update(sentence)) {
			Sentence findSentence = sentenceMapper.findById(sentence.getSentence_id());
			sentenceResponseDto = SentenceResponseDto.builder()
					.sentence_id(findSentence.getSentence_id())
					.content(findSentence.getContent())
					.modifieddate(findSentence.getModifieddate())
					.build();
		}
		
		return sentenceResponseDto;
	}
	
	public int removeSentence(Long sno) {
		return sentenceMapper.deleteById(sno);
	}
}
