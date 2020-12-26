package com.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.config.RootConfig;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ReplyMapperTest {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void replyMapperTest() {
		log.info(replyMapper);
	}
}
