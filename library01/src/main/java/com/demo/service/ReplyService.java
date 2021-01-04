package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Reply;
import com.demo.dto.ReplyRequestDto;
import com.demo.dto.ReplyResponseDto;
import com.demo.mapper.BoardMapper;
import com.demo.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<ReplyResponseDto> getReplies(Long board_id) {
		return replyMapper.getReplies(board_id).stream()
					.map(reply -> ReplyResponseDto.builder()
									.reply_id(reply.getReply_id())
									.reply(reply.getReply())
									.replier(reply.getReplier())
									.updateddate(reply.getUpdateddate())
									.build())
					.collect(Collectors.toList());
	}
	
	public ReplyResponseDto addReply(ReplyRequestDto replyRequestDto, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		boardMapper.updateReplyCount(replyRequestDto.getBoard_id(), 1);
		
		Reply reply = Reply.builder()
						.reply(replyRequestDto.getReply())
						.replier(userId)
						.board_id(replyRequestDto.getBoard_id())
						.build();
		
		ReplyResponseDto replyResponseDto = null;
		if(replyMapper.insert(reply)) {
			Reply findReply = replyMapper.getReply(reply.getReply_id());
			replyResponseDto = ReplyResponseDto.builder()
											.reply_id(findReply.getReply_id())
											.reply(findReply.getReply())
											.replier(findReply.getReplier())
											.updateddate(findReply.getUpdateddate())
											.build();
		}
		
		return replyResponseDto;
	}
	
	public void removeReply(Long reply_id) {
		Reply reply = replyMapper.getReply(reply_id);
		boardMapper.updateReplyCount(reply.getBoard_id(), -1);
		
		replyMapper.delete(reply_id);
	}
	
	public ReplyResponseDto updateReply(ReplyRequestDto replyRequestDto) {
		Reply reply = Reply.builder()
				.reply(replyRequestDto.getReply())
				.reply_id(replyRequestDto.getReply_id())
				.build();
		
		ReplyResponseDto replyResponseDto = null;
		if(replyMapper.update(reply)) {
			Reply findReply = replyMapper.getReply(reply.getReply_id());
			
			replyResponseDto = ReplyResponseDto.builder()
					.replier(findReply.getReplier())
					.reply_id(findReply.getReply_id())
					.reply(findReply.getReply())
					.updateddate(findReply.getUpdateddate())
					.build();
		}
		
		return replyResponseDto;
	}
}
