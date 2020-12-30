package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ReviewVO;
import com.demo.dto.ReviewRequestDto;
import com.demo.dto.ReviewResponseDto;
import com.demo.mapper.ReviewMapper;

@Service
public class ReviewService{
	
	@Autowired
	private ReviewMapper reviewMapper;

	public ReviewResponseDto register(ReviewRequestDto reviewRequestDto) {
		ReviewVO review = ReviewVO.builder()
				.bno(reviewRequestDto.getBno())
				.content(reviewRequestDto.getContent())
				.rating(reviewRequestDto.getRating())
				.build();

		ReviewResponseDto reviewResponseDto = null;
		if(reviewMapper.save(review)){
			ReviewVO findReview = reviewMapper.findByBookId(review.getBno());
			reviewResponseDto = ReviewResponseDto.builder()
					.bno(findReview.getBno())
					.content(findReview.getContent())
					.modifieddate(findReview.getModifieddate())
					.build();
		}
		
		return reviewResponseDto;
	}
	
	public ReviewResponseDto modify(ReviewRequestDto reviewRequestDto) {
		ReviewVO review = ReviewVO.builder()
				.bno(reviewRequestDto.getBno())
				.content(reviewRequestDto.getContent())
				.rating(reviewRequestDto.getRating())
				.build();
		
		ReviewResponseDto reviewResponseDto = null;
		if(reviewMapper.update(review)){
			ReviewVO findReview = reviewMapper.findByBookId(review.getBno());
			reviewResponseDto = ReviewResponseDto.builder()
					.bno(findReview.getBno())
					.content(findReview.getContent())
					.modifieddate(findReview.getModifieddate())
					.build();
		}
		
		return reviewResponseDto;
	}

	public ReviewVO getReview(Long bno) {
		return reviewMapper.findByBookId(bno);
	}

	public int delete(Long bno) {
		return reviewMapper.delete(bno);
	}
}
