package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.domain.Board;
import com.demo.dto.BoardDto;
import com.demo.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public void addPost(BoardDto boardDto, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		Board board = Board.builder()
				.title(boardDto.getTitle())
				.content(boardDto.getContent())
				.writer(userId)
				.build();
		
		boardMapper.insert(board);
	}
	
	public List<Board> getPostList() {
		return boardMapper.getList();
	}
	
	public Board getPostPage(Long id) {
		return boardMapper.getPost(id);
	}
}
