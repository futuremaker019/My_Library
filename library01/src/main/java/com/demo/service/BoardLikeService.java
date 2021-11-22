package com.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.BoardLike;
import com.demo.mapper.BoardLikeMapper;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardLikeService {
	private final BoardLikeMapper boardLikeMapper;
	
	public BoardLike addLike(Long board_id, String userId) {
		BoardLike boardLike = BoardLike.builder()
				.board_id(board_id)
				.userId(userId)
				.build();

		boardLikeMapper.save(boardLike);
		
		return boardLike;
	}
	
	public int getTotalLike(Long board_id) {
		return boardLikeMapper.totalLike(board_id);
	}
	
	public BoardLike getLikeId(Authentication authetication) {
		
		
		return null;
	}
}
