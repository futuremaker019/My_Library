package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Criteria;
import com.demo.domain.SentenceVO;
import com.demo.mapper.SentenceMapper;

import lombok.Setter;


@Service
public class SentenceServiceImple implements SentenceService {
	
	@Setter(onMethod_ = @Autowired)
	private SentenceMapper sentenceMapper;

	@Override
	public int register(SentenceVO sentence) {
		return sentenceMapper.insert(sentence);
	}

	@Override
	public SentenceVO getOne(Long sno) {
		return sentenceMapper.read(sno);
	}

	@Override
	public int remove(Long sno) {
		return sentenceMapper.delete(sno);
	}

	@Override
	public int modify(SentenceVO sentence) {
		return sentenceMapper.update(sentence);
	}

	@Override
	public List<SentenceVO> getListWithPaging(Criteria criteria, Long bno) {
		return sentenceMapper.getListWithPaging(criteria, bno);
	}
}
