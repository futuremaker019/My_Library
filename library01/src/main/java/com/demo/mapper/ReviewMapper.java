package com.demo.mapper;

import com.demo.domain.ReviewVO;

public interface ReviewMapper {

	public boolean save(ReviewVO reviewVO);
	
	public ReviewVO findByBookId(Long book_id);
	
	public int delete(Long book_id);
	
	public boolean update(ReviewVO reviewVO);
}
