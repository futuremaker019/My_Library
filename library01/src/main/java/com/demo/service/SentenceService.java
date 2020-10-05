package com.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.Criteria;
import com.demo.domain.SentenceVO;

public interface SentenceService {

	public int register(SentenceVO sentence);
	
	public SentenceVO getOne(Long sno);
	
	public int remove(Long sno);
	
	public int modify(SentenceVO sentence);
	
	public List<SentenceVO> getListWithPaging(
					@Param("criteria")Criteria criteria,
					@Param("bno")Long bno);
}
