package com.demo.mapper;

import java.util.List;

import com.demo.domain.Sentence;

public interface SentenceMapper {

	public boolean save(Sentence sentence);
	
	public Sentence findById(Long sentence_id);
	
	public List<Sentence> findAllByBookId(Long bno);
	
	public int deleteById(Long sentence_id);
	
	public boolean update(Sentence sentence);
}
