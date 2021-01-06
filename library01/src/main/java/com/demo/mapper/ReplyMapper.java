package com.demo.mapper;

import java.util.List;

import com.demo.domain.Reply;

public interface ReplyMapper {
	public boolean insert(Reply reply);
	
	public Reply findById(Long reply_id);
	
	public List<Reply> findAllByBoardId(Long board_id);
	
	public boolean delete(Long reply_id);
	
	public boolean update(Reply reply);
}
