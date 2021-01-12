package com.demo.mapper;

import com.demo.domain.Review;

public interface ReviewMapper {

	public boolean save(Review reviewVO);
	
	public Review findByBookId(Long book_id);
	
	public int delete(Long book_id);
	
	public boolean update(Review reviewVO);
}
