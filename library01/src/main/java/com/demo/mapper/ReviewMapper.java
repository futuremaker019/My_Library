package com.demo.mapper;

import com.demo.domain.ReviewVO;

public interface ReviewMapper {

	public boolean save(ReviewVO reviewVO);
	
	public ReviewVO findByBookId(Long bno);
	
	public int delete(Long bno);
	
	public boolean update(ReviewVO reviewVO);
}
