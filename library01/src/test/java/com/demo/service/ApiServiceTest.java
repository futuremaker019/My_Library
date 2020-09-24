package com.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ApiServiceTest {

	@Setter(onMethod_ = @Autowired)
	private ApiService apiService;
	
	@Test
	public void testApiService() {
		
		String title = "읽기 좋은 코드가 좋은 코드다";
		String isbn = "897914914X";
		String publisher = "한빛미디어";
		String thumbnail = "https://search1.kakaocdn.net/thumb/R12";
		String url = "https://search.daum.net/search?w=bookpage&b";
		
		Date datetime = new Date(2020, 5, 11);
		
		List<AuthorVO> authors = new ArrayList<>();
		authors.add(new AuthorVO("897914914X", "톰 크루즈"));
		authors.add(new AuthorVO("897914914X", "더스틴 호프만"));
		
		BookVO bookVO = new BookVO();
		bookVO.setTitle(title);
		bookVO.setIsbn(isbn);
		bookVO.setPublisher(publisher);
		bookVO.setThumbnail(thumbnail);
		bookVO.setUrl(url);
		
		bookVO.setDatetime(datetime);
		
		log.info("bookVO" + bookVO);
		log.info("authors" + authors);
		
		apiService.register(bookVO, authors);
	}
}
