package com.demo.mapper;

import com.demo.domain.BoardLike;

public interface BoardLikeMapper {
	
	public int totalLike(Long board_id);
	
	public boolean save(BoardLike boardLike);

	public BoardLike findByUserId(String userId);
	
	public void deleteUserId(String userId);
}
