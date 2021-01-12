package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Author;
import com.demo.domain.Book;
import com.demo.domain.Criteria;
import com.demo.domain.Member;
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
	
	public List<Book> getListWithPaging(Criteria cri, Authentication authentication) {
		String userId = getUserId(authentication);
		
		return bookMapper.getListWithPaging(cri, userId);
	}

	public int getTotal(Criteria criteria, Authentication authentication) {
		String userId = getUserId(authentication);
		
		return bookMapper.getTotalCount(criteria, userId);
	}

	public List<Book> getSearchListWithPaging(Criteria criteria) {
		return bookMapper.getSearchListWithPaging(criteria);
	}

	public int getTotalSearchItem(Criteria criteria) {
		return bookMapper.getTotalSearchCount(criteria);
	}

	public Book getBook(Long book_id) {
		return bookMapper.findById(book_id);
	}
	
	public List<Book> getBooksImage(Authentication authentication){
		List<Book> books = null;
		
		if(authentication != null) {
			String userId = getUserId(authentication);
			
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
	
	public void register(BookRequestDto bookRequestDto, List<Author> authors, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = (String) userDetails.getUsername();
		
		Member member = memberMapper.findByUserId(userId); 
		
		Book bookVO = bookRequestDto.toEntity();
		bookVO.saveMemberInfo(member.getMember_id(), member.getUserId());
		
		if (bookMapper.insert(bookVO)) {
			authors.forEach(author -> {
				author.saveBookId(bookVO.getBook_id());
				authorMapper.insert(author);
			});
		}
	}
	
	public Boolean verifyExistedBook(BookRequestDto bookRequestDto) {
		List<Book> findBookList = bookMapper.getBookByIsbnUsingLike(bookRequestDto.getIsbn());
		
		if(findBookList != null && findBookList.size() != 0) {
			Boolean hasBook = findBookList.stream()
				.anyMatch(book -> book.getUserId().equals(bookRequestDto.getUserId()));
				
			return hasBook;
		}
		
		return false;
	}
	
	private String getUserId(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return (String) userDetails.getUsername();
	}
	
	private Book findBookByIsbn(String isbn) {
		return bookMapper.getBookByIsbn(isbn);
	}
}
