package com.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class})
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	@Before
	public void setup() {
//		Board board1 = Board.builder()
//				.board_id(1L).title("타이틀1").content("내용1").writer("작가").build();
//		Board board2 = Board.builder()
//				.board_id(2L).title("타이틀2").content("내용2").writer("작가2").build();
		
	}

	@Test
	public void getOneElement() {
//		boardService.getList().forEach(post -> log.info(post));
	}
}