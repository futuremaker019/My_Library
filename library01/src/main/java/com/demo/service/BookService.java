package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.mapper.AuthorMapper;
import com.demo.mapper.BookMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
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

	public BookVO getBook(Long bno) {
		return bookMapper.getOne(bno);
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
	
	public int remove(Long bno) {
		return bookMapper.delete(bno);
	}

	@Transactional
	public void removeBooks(List<Long> bnos) {
		bnos.forEach(bno -> bookMapper.delete(bno));
	}
}
