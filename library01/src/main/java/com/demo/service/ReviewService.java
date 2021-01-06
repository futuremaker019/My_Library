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
				.book_id(reviewRequestDto.getBook_id())
				.content(reviewRequestDto.getContent())
				.rating(reviewRequestDto.getRating())
				.build();

		ReviewResponseDto reviewResponseDto = null;
		if(reviewMapper.save(review)){
			ReviewVO findReview = reviewMapper.findByBookId(review.getBook_id());
			reviewResponseDto = ReviewResponseDto.builder()
					.book_id(findReview.getBook_id())
					.content(findReview.getContent())
					.modifieddate(findReview.getModifieddate())
					.build();
		}
		
		return reviewResponseDto;
	}
	
	public ReviewResponseDto modify(ReviewRequestDto reviewRequestDto) {
		ReviewVO review = ReviewVO.builder()
				.book_id(reviewRequestDto.getBook_id())
				.content(reviewRequestDto.getContent())
				.rating(reviewRequestDto.getRating())
				.build();
		
		ReviewResponseDto reviewResponseDto = null;
		if(reviewMapper.update(review)){
			ReviewVO findReview = reviewMapper.findByBookId(review.getBook_id());
			reviewResponseDto = ReviewResponseDto.builder()
					.book_id(findReview.getBook_id())
					.content(findReview.getContent())
					.modifieddate(findReview.getModifieddate())
					.build();
		}
		
		return reviewResponseDto;
	}

	public ReviewVO getReview(Long book_id) {
		return reviewMapper.findByBookId(book_id);
	}

	public int delete(Long book_id) {
		return reviewMapper.delete(book_id);
	}
}
