package com.demo.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;
import com.demo.config.ServletConfig;
import com.demo.domain.Book;
import com.demo.domain.Criteria;
import com.demo.mapper.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@Log4j
public class BookMapperTest {

	@Setter(onMethod_ = @Autowired)
	private BookMapper bookMapper;

//	@Test
//	public void testInsert() {
//		
//		BookVO bookVO = new BookVO();

//		bookVO.setTitle("코드로 배우는 스프링 웹프로젝트(개정판)");
////		bookVO.setAuthors("구멍가게 코딩단");
//		bookVO.setIsbn("118918401X 9791189184018");
//		bookVO.setPublisher("남가람북스");
//		bookVO.setThumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3755513%3Ftimestamp%3D20200911150231");
//		bookVO.setUrl("https://search.daum.net/search?w=bookpage&bookId=3755513&q=%EC%BD%94%EB%93%9C%EB%A1%9C+%EB%B0%B0%EC%9A%B0%EB%8A%94+%EC%8A%A4%ED%94%84%EB%A7%81+%EC%9B%B9+%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%28%EA%B0%9C%EC%A0%95%ED%8C%90%29");
//		
//		bookMapper.insert(bookVO);
//		
//		log.info(bookVO);
//	}

//	@Test
//	public void testPaging() {
//		Criteria cri = new Criteria();
//		
//		cri.setPageNum(2);
//		cri.setAmount(20);
//
//		List<BookVO> list = bookMapper.getListWithPaging(cri);
//
//		list.forEach(book -> log.info(book.getTitle()));
//	}
	
//	@Test
//	public void testSearch() {
//		Criteria cri = new Criteria();
//		
//		cri.setPageNum(1);
//		cri.setAmount(20);
//		cri.setType("A");
//		cri.setKeyword("최");
//		
//		List<BookVO> list = bookMapper.getSearchListWithPaging(cri);
//		
//		list.forEach(book -> log.info(book.getTitle()));
//	}
	
//	@Test
//	public void testSearchResultCount() {
//		Criteria cri = new Criteria();
//	
//		cri.setPageNum(1);
//		cri.setAmount(20);
//		
//		int count = bookMapper.getTotalSearchCount(cri);
//		
//		log.info("Search count : " +  count);
//	}
	
	/*
	 * @Test public void testGetOne() { log.info(bookMapper.getOne(7L)); }
	 */
	
	/*
	 * @Test public void testDeleteBook() { int count = bookMapper.delete(1L);
	 * 
	 * log.info("delete count: " + count); }
	 */
	
	@Test
	public void testGetBookByIsbn() {
		String isbn = "1130613372";
		String isbn2 = "8901130467";
		
//		BookVO book = bookMapper.getBook(isbn2);
		
		/* assertThat(book.getIsbn(), is("1130613372")); */
//		log.info(book);
	}
}
