package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Attachment;
import com.demo.domain.Board;
import com.demo.domain.Criteria;
import com.demo.dto.AttachmentDto;
import com.demo.dto.BoardDto;
import com.demo.mapper.BoardMapper;
import com.demo.mapper.AttachmentMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
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
		
		if(boardMapper.insert(board) == 1) {
			if(attachmentDtos != null) {
				List<Attachment> attachments = attachmentDtos.stream()
						.map(attachmentDto -> new Attachment(attachmentDto.getFileName(), attachmentDto.getUploadPath(), attachmentDto.getUuid()))
						.collect(Collectors.toList());
				
				for (Attachment attachment : attachments) {
					attachment.setBoard_id(board.getBoard_id());
					attachmentMapper.insert(attachment);
				}
			}
		}
	}
	
	public void modifyPost(Long board_id, BoardDto boardDto, List<AttachmentDto> attachmentDtos) {
		Board board = boardMapper.getPost(board_id);
		board.updateElements(boardDto.getTitle(), boardDto.getContent());
		
		if(boardMapper.updatePost(board) == 1) {
			if(attachmentDtos != null) {
				List<Attachment> attachments = attachmentDtos.stream()
						.map(attachmentDto -> new Attachment(attachmentDto.getFileName(), attachmentDto.getUploadPath(), attachmentDto.getUuid()))
						.collect(Collectors.toList());
				
				for (Attachment attachment : attachments) {
					attachment.setBoard_id(board.getBoard_id());
					attachmentMapper.insert(attachment);
				}
			}
		}
	}
	
	public List<Board> getPostList() {
		return boardMapper.getList();
	}
	
	public List<Board> getPostListWithPaging(Criteria criteria) {
		return boardMapper.getListWithPaging(criteria);
	}
	
	public Board getPostPage(Long id) {
		return boardMapper.getPost(id);
	}
	
	public int getTotalPost() {
		return boardMapper.total();
	}
}
