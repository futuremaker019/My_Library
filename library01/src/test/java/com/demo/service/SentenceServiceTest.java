package com.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SentenceServiceTest {

	@Setter(onMethod_ = @Autowired)
	private SentenceService sentenceService;
	
	@Test
	public void testSentenceList() {
		Long targetBno = 45L;
				
		Criteria criteria = new Criteria();
		
		sentenceService.getListWithPaging(criteria, targetBno)
			.forEach(sentence -> log.info(sentence));
	}
}
