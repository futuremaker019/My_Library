package com.demo.service;

import com.demo.domain.ReviewVO;

public interface ReviewService {

	public int register(ReviewVO reviewVO);
	
	public ReviewVO getReview(Long bno);
	
	public int delete(Long bno);
	
	public int modify(ReviewVO reviewVO);
}
