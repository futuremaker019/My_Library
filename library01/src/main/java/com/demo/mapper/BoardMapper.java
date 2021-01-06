package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.domain.Board;
import com.demo.domain.Criteria;

public interface BoardMapper {
	
	public boolean insert(Board board);
	
	public List<Board> getList();
	
	public List<Board> getListWithPaging(Criteria criteria);

	public Board getPost(Long id);
	
	public List<Board> getPostsTop4();
	
	public int total();
	
	public boolean updatePost(Board board);
	
	public boolean removeSinglePost(Long board_id);
	
	public void updateReplyCount(@Param("board_id") Long board_id, @Param("count") int count);
}
