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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyMapper replyMapper;
	private final BoardMapper boardMapper;
	
	public List<ReplyResponseDto> getReplies(Long board_id) {
		return replyMapper.findAllByBoardId(board_id).stream()
					.map(reply -> ReplyResponseDto.builder()
									.reply_id(reply.getReply_id())
									.reply(reply.getReply())
									.replier(reply.getReplier())
									.updateddate(reply.getUpdateddate())
									.build())
					.collect(Collectors.toList());
	}
	
	public ReplyResponseDto getReply(Long reply_id) {
		Reply reply = replyMapper.findById(reply_id);
		
		return ReplyResponseDto.builder()
				.reply_id(reply.getReply_id())
				.reply(reply.getReply())
				.replier(reply.getReplier())
				.updateddate(reply.getUpdateddate())
				.build();
	}
	
	public ReplyResponseDto addReply(ReplyRequestDto replyRequestDto, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		Reply reply = Reply.builder()
						.reply(replyRequestDto.getReply())
						.replier(userId)
						.board_id(replyRequestDto.getBoard_id())
						.build();
		
		ReplyResponseDto replyResponseDto = null;
		if(replyMapper.insert(reply)) {
			Reply findReply = replyMapper.findById(reply.getReply_id());
			replyResponseDto = ReplyResponseDto.builder()
											.reply_id(findReply.getReply_id())
											.reply(findReply.getReply())
											.replier(findReply.getReplier())
											.updateddate(findReply.getUpdateddate())
											.build();
			boardMapper.updateReplyCount(replyRequestDto.getBoard_id(), 1);
		}
		
		return replyResponseDto;
	}
	
	public void removeReply(Long reply_id) {
		Reply reply = replyMapper.findById(reply_id);
		if(replyMapper.delete(reply_id)) {
			boardMapper.updateReplyCount(reply.getBoard_id(), -1);
		}
	}
	
	public ReplyResponseDto updateReply(ReplyRequestDto replyRequestDto) {
		Reply reply = Reply.builder()
				.reply(replyRequestDto.getReply())
				.reply_id(replyRequestDto.getReply_id())
				.build();
		
		ReplyResponseDto replyResponseDto = null;
		if(replyMapper.update(reply)) {
			Reply findReply = replyMapper.findById(reply.getReply_id());
			
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
