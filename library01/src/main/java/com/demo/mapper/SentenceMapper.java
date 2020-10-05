package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.Criteria;
import com.demo.domain.SentenceVO;

public interface SentenceMapper {

	public int insert(SentenceVO sentenceVO);
	
	public SentenceVO read(Long sno);
	
	public int delete(Long sno);
	
	public int update(SentenceVO sentenceVO);
	
	public List<SentenceVO> getListWithPaging(
			@Param("cri") Criteria criteria, 
			@Param("bno") Long bno);
}
