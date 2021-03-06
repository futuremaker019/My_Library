package com.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;
import com.demo.config.ServletConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@Log4j
public class AuthorServiceTest {

	/*
	 * @Setter(onMethod_ = @Autowired) private AuthorService authorService;
	 */
	
//	@Test
//	public void testGetAuhorsList() {
//		
//		authorService.getAuthorList().forEach(author -> log.info(author));
//	}
}
