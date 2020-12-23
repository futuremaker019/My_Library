package com.demo.mapper;

import java.util.List;

import com.demo.domain.Board;

public interface BoardMapper {
	
	public int insert(Board board);
	
	public List<Board> getList();

	public Board getPost(Long id);
}
