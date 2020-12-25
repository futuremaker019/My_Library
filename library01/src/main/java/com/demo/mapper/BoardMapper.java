package com.demo.mapper;

import java.util.List;

import com.demo.domain.Board;
import com.demo.domain.Criteria;

public interface BoardMapper {
	
	public int insert(Board board);
	
	public List<Board> getList();
	
	public List<Board> getListWithPaging(Criteria criteria);

	public Board getPost(Long id);
	
	public int total();
	
	public int updatePost(Board board);
	
	public boolean removeSinglePost(Long board_id);
}
