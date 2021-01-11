package com.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.config.RootConfig;
import com.demo.config.SecurityConfig;
import com.demo.config.ServletConfig;
import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.dto.BookRequestDto;
import com.demo.mapper.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class, SecurityConfig.class})
@Rollback
@Log4j
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Test
//	public void testService() {
//		
//		log.info(bookService);
//		
//		assertNotNull(bookService);
//	}
	
//	@Test
//	public void testRegister() {
//		
//		BookVO bookVO = new BookVO();
//		
//		bookVO.setTitle("코드로 배우는 스프링 웹프로젝트(개정판)");
//		bookVO.setIsbn("118918401X 9791189184018");
//		bookVO.setPublisher("남가람북스");
//		bookVO.setThumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3755513%3Ftimestamp%3D20200911150231");
//		bookVO.setUrl("https://search.daum.net/search?w=bookpage&bookId=3755513&q=%EC%BD%94%EB%93%9C%EB%A1%9C+%EB%B0%B0%EC%9A%B0%EB%8A%94+%EC%8A%A4%ED%94%84%EB%A7%81+%EC%9B%B9+%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%28%EA%B0%9C%EC%A0%95%ED%8C%90%29");
//		
//		bookService.register(bookVO);
//		
//		log.info("bookVO : " + bookVO);
//	}
	
//	@Test
//	public void testGetList() {
//		bookService.getListWithPaging(new Criteria(2, 20)).forEach(book -> log.info(book.getTitle()));
//	}
	
//	@Test
//	public void testTotalCount() {
//		Criteria criteria = new Criteria(); 
//		
//		log.info(bookService.getTotal(criteria));
//	}
	
	/*
	 * @Test public void testBookDelete() { //when List<Long> bnos =
	 * Arrays.asList(80L, 81L, 82L); bookService.removeBooks(bnos);
	 * 
	 * BookVO book = bookService.getBook(80L);
	 * 
	 * //then assertNull(book); }
	 */
	
	// spring security 설정때문에 테스트를 진행할수 없는 듯하다....
	
	  @Test 
	  public void hasBook_findBookWhichHasRequiredIsbnAndUserId_false() {
		  
		  //given
		  String isbn = "8968480672";
		  String userId = "noah00o";
		  
		  BookRequestDto bookRequestDto = BookRequestDto.builder() 
				  .isbn(isbn).userId(userId).build();
		  
		  //when
		  Boolean hasBook = bookService.verifyExistedBook(bookRequestDto);
		  
		  //then
		  assertThat(hasBook, is(true));
	  }
	  
	  @Test
	  public void hasBook_checkIfBookExists_true() {
		  List<AuthorVO> authors = new ArrayList<AuthorVO>();
		  AuthorVO author = new AuthorVO(null, "테스트작가");
		  authors.add(author);
		  
		  String isbn = "8968480672";
		  String title = "테스트 타이틀";
		  String userId = "noah00o";
		  String thumbnail = "테스트 섬네일";
		  String url = "테스트 url";
		  Date datetime = new Date(121, 0, 9); 
		  
		  BookRequestDto bookRequestDto = BookRequestDto.builder()
				  .isbn(isbn)
				  .title(title)
				  .thumbnail(thumbnail)
				  .url(url)
				  .datetime(datetime)
				  .userId(userId)
				  .authors(authors)
				  .build();
		  //  authentication 객체는 어떻게 test 코드에서 받아줘야 하나...
			/* bookService.register(bookRequestDto, authors, authentication); */
	  }
}
