package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;

import com.demo.domain.Book;
import com.demo.domain.Criteria;

public interface BookMapper {
	
	public List<Book> getListWithPaging(
			@Param("criteria") Criteria criteria, 
			@Param("userId") String userId);
	
	public List<Book> getSearchListWithPaging(Criteria criteria);
	
	public Book findById(Long book_id);
	
	public List<Book> getThumbnails(String userId);
	
	public Book getBookByIsbn(String isbn);
	
	public List<Book> getBookByIsbnUsingLike(String isbn);

	public boolean insert(Book bookVO);
	
	public int getTotalCount(
			@Param("criteria") Criteria criteria, 
			@Param("userId") String userId);
	
	public int getTotalSearchCount(Criteria criteria);

	public int delete(Long book_id);
}
