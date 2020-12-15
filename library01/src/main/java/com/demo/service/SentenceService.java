package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Criteria;
import com.demo.domain.SentenceVO;
import com.demo.mapper.SentenceMapper;

import lombok.Setter;


@Service
public class SentenceService{
	
	@Autowired
	private SentenceMapper sentenceMapper;

	public int register(SentenceVO sentence) {
		return sentenceMapper.insert(sentence);
	}

	public SentenceVO getOne(Long sno) {
		return sentenceMapper.read(sno);
	}

	public int remove(Long sno) {
		return sentenceMapper.delete(sno);
	}

	public int modify(SentenceVO sentence) {
		return sentenceMapper.update(sentence);
	}

	public List<SentenceVO> getListWithPaging(Criteria criteria, Long bno) {
		return sentenceMapper.getListWithPaging(criteria, bno);
	}
}
