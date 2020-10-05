package com.demo.mapper;

import com.demo.domain.ReviewVO;

public interface ReviewMapper {

	public int insert(ReviewVO reviewVO);
	
	public ReviewVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(ReviewVO reviewVO);
}
