package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.domain.MemberVO;
import com.demo.dto.BookRequestDto;
import com.demo.mapper.AuthorMapper;
import com.demo.mapper.BookMapper;
import com.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Transactional
public class BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	public List<BookVO> getListWithPaging(Criteria cri, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = (String) userDetails.getUsername();
		
		return bookMapper.getListWithPaging(cri, userId);
	}

	public int getTotal(Criteria criteria, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = (String) userDetails.getUsername();
		
		return bookMapper.getTotalCount(criteria, userId);
	}

	public List<BookVO> getSearchListWithPaging(Criteria criteria) {
		return bookMapper.getSearchListWithPaging(criteria);
	}

	public int getTotalSearchItem(Criteria criteria) {
		return bookMapper.getTotalSearchCount(criteria);
	}

	public BookVO getBook(Long book_id) {
		return bookMapper.getOne(book_id);
	}
	
	public List<BookVO> getBooksImage(Authentication authentication){
		List<BookVO> books = null;
		
		if(authentication != null) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String userId = (String) userDetails.getUsername();
			
			books = bookMapper.getThumbnails(userId);
			return books;
		}
		return books;
	}
	
	public int remove(Long book_id) {
		return bookMapper.delete(book_id);
	}
	
	public void removeBooks(List<Long> book_ids) {
		book_ids.forEach(book_id -> bookMapper.delete(book_id));
	}
	
	public void register(BookRequestDto bookRequestDto, List<AuthorVO> authors, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = (String) userDetails.getUsername();
		
		MemberVO member = memberMapper.findByUserId(userId); 
		
		BookVO bookVO = bookRequestDto.toEntity();
		bookVO.saveMemberInfo(member.getMember_id(), member.getUserId());
		
		if (bookMapper.insert(bookVO)) {
			for (AuthorVO author : authors) {
				author.setBook_id(bookVO.getBook_id());
				authorMapper.insert(author);
			}
		}
	}
	
	public Boolean verifyExistedBook(BookRequestDto bookRequestDto, Authentication authentication) {
		List<BookVO> findBookList = bookMapper.getBookByIsbnUsingLike(bookRequestDto.getIsbn());
		
		if(findBookList != null && findBookList.size() != 0) {
			Boolean hasBook = findBookList.stream()
				.anyMatch(book -> book.getUserId().equals(bookRequestDto.getUserId()));
				
			return hasBook;
		}
		
		return false;
	}
	
	private BookVO findBookByIsbn(String isbn) {
		return bookMapper.getBookByIsbn(isbn);
	}
}
