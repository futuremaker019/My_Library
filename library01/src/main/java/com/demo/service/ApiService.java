package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.dto.BookDto;
import com.demo.mapper.AuthorMapper;
import com.demo.mapper.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class ApiService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;

	
	public void register(BookDto bookDto, List<AuthorVO> authors, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = (String) userDetails.getUsername();
		
		BookVO bookVO = bookDto.toEntity();
		log.info("bookVO : " + bookVO);
		
		bookVO.setUserId(userId);
		
		if (bookMapper.insert(bookVO) == 1) {
			BookVO findBook = findBookByIsbn(bookVO.getIsbn());
			
			for (AuthorVO author : authors) {
				author.setIsbn(bookVO.getIsbn());
				author.setBno(findBook.getBno());
				authorMapper.insert(author);
			}
		}
	}

	public BookVO findBookByIsbn(String isbn) {
		return bookMapper.getBookByIsbn(isbn);
	}
	
	public BookDto findBookByIsbnUsingLike(String isbn) {
		BookVO bookVO = bookMapper.getBookByIsbnUsingLike(isbn).get(0);
		String findIsbn = bookVO.getIsbn();
		String isbnWithoutUuid = findIsbn.substring(0, findIsbn.indexOf("-"));
		
		log.info("isbnWithoutUuid : " + isbnWithoutUuid);
		
		BookDto bookDto = null;
		
		if(bookVO != null) {
			bookDto = BookDto.builder()
					.isbn(isbnWithoutUuid)
					.build();
		}
		return bookDto;
	}
}
