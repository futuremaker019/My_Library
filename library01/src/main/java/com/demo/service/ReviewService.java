package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ReviewVO;
import com.demo.mapper.ReviewMapper;

import lombok.Setter;

@Service
public class ReviewService{
	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	public int register(ReviewVO reviewVO) {
		return reviewMapper.insert(reviewVO);
	}

	public ReviewVO getReview(Long bno) {
		return reviewMapper.read(bno);
	}

	public int delete(Long bno) {
		return reviewMapper.delete(bno);
	}

	public int modify(ReviewVO reviewVO) {
		return reviewMapper.update(reviewVO);
	}
}
