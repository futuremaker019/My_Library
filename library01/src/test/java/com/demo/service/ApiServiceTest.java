package com.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;
import com.demo.config.ServletConfig;
import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@Log4j
public class ApiServiceTest {

	@Autowired
	private BookService bookService;
	
	@Test
	public void testApiService() {
		
		String title = "�씫湲� 醫뗭� 肄붾뱶媛� 醫뗭� 肄붾뱶�떎";
		String isbn = "897914914X";
		String publisher = "�븳鍮쏅�몃뵒�뼱";
		String thumbnail = "https://search1.kakaocdn.net/thumb/R12";
		String url = "https://search.daum.net/search?w=bookpage&b";
		
		Date datetime = new Date(2020, 5, 11);
		
		List<AuthorVO> authors = new ArrayList<>();
//		authors.add(new AuthorVO("897914914X", "�넱 �겕猷⑥쫰"));
//		authors.add(new AuthorVO("897914914X", "�뜑�뒪�떞 �샇�봽留�"));
		
		BookVO bookVO = new BookVO();
		bookVO.setTitle(title);
		bookVO.setIsbn(isbn);
		bookVO.setPublisher(publisher);
		bookVO.setThumbnail(thumbnail);
		bookVO.setUrl(url);
		
		bookVO.setDatetime(datetime);
		
		log.info("bookVO" + bookVO);
		log.info("authors" + authors);
		
//		apiService.register(bookVO, authors);
	}
}
