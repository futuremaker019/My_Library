package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookMapper {
	
	public List<BookVO> getListWithPaging(
			@Param("criteria") Criteria criteria, 
			@Param("userId") String userId);
	
	public List<BookVO> getSearchListWithPaging(Criteria criteria);
	
	public BookVO getOne(Long book_id);
	
	public List<BookVO> getThumbnails(String userId);
	
	public BookVO getBookByIsbn(String isbn);
	
	public List<BookVO> getBookByIsbnUsingLike(String isbn);

	public boolean insert(BookVO bookVO);
	
	public int getTotalCount(
			@Param("criteria") Criteria criteria, 
			@Param("userId") String userId);
	
	public int getTotalSearchCount(Criteria criteria);

	public int delete(Long book_id);
}
