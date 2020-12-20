package com.demo.mapper;

import java.util.List;

import com.demo.domain.Board;

public interface BoardMapper {
	
	public void insert(Board board);
	
	public List<Board> getList();

	public Board getPost(Long id);
}
