package com.demo.mapper;

import java.util.List;

import com.demo.domain.Reply;

public interface ReplyMapper {
	public int insert(Reply reply);
	
	public Reply getReply(Long reply_id);
	
	public List<Reply> getReplies(Long board_id);
	
	public void delete(Long reply_id);
	
	public void update(Reply reply);
}
