package com.demo.persistence;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.SentenceVO;
import com.demo.mapper.SentenceMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SentenceMapperTest {
	
	private Long[] bnoArray = {1L, 2L, 3L, 4L, 5L};

	@Setter(onMethod_ = @Autowired)
	private SentenceMapper sentenceMapper;
	
	@Test
	public void testMapperExited() {
		log.info(sentenceMapper);
	}
	
	
	@Test
	public void insertTest() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			SentenceVO sentence = SentenceVO.builder()
					.bno(bnoArray[i % 5])
					.sentence("sentence " + i)
					.build();

			sentenceMapper.insert(sentence);
		});
	}
	 
	
	/*
	 * @Test public void testRead() { log.info(sentenceMapper.read(10L)); }
	 * 
	 * @Test public void testModify() { SentenceVO sentence =
	 * sentenceMapper.read(10L);
	 * 
	 * sentence.setSentence("how are you");
	 * 
	 * int count = sentenceMapper.update(sentence);
	 * 
	 * log.info(count); }
	 */
}
