package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ReviewVO;
import com.demo.mapper.ReviewMapper;

import lombok.Setter;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper reviewMapper;

	@Override
	public int register(ReviewVO reviewVO) {
		return reviewMapper.insert(reviewVO);
	}

	@Override
	public ReviewVO getReview(Long bno) {
		return reviewMapper.read(bno);
	}

	@Override
	public int delete(Long bno) {
		return reviewMapper.delete(bno);
	}

	@Override
	public int modify(ReviewVO reviewVO) {
		return reviewMapper.update(reviewVO);
	}
}
