package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.domain.Attachment;
import com.demo.domain.Board;
import com.demo.dto.AttachmentDto;
import com.demo.dto.BoardDto;
import com.demo.mapper.BoardMapper;
import com.demo.mapper.AttachmentMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	public void addPost(BoardDto boardDto, List<AttachmentDto> attachmentDtos, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.writer(userId)
				.build();
		
		List<Attachment> attachments = attachmentDtos.stream()
			.map(attachmentDto -> new Attachment(attachmentDto.getFileName(), attachmentDto.getUploadPath(), attachmentDto.getUuid()))
			.collect(Collectors.toList());
		
		if(boardMapper.insert(board) == 1) {
			for (Attachment attachment : attachments) {
				attachment.setBoard_id(board.getBoard_id());
				attachmentMapper.insert(attachment);
			}
		}
	}
	
	public List<Board> getPostList() {
		return boardMapper.getList();
	}
	
	public Board getPostPage(Long id) {
		return boardMapper.getPost(id);
	}
}
