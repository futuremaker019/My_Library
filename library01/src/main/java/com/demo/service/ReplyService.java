package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.ReplyResponseDto;
import com.demo.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	public List<ReplyResponseDto> getReplies(Long board_id) {
		return replyMapper.getReplies(board_id).stream()
					.map(reply -> ReplyResponseDto.builder()
									.reply_id(reply.getReply_id())
									.reply(reply.getReply())
									.replyer(reply.getReplyer())
									.createddate(reply.getCreateddate())
									.build())
					.collect(Collectors.toList());
	}
}
