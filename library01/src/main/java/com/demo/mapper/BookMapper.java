package com.demo.mapper;

import java.util.List;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookMapper {
	
	public List<BookVO> getListWithPaging(Criteria criteria);
	
	public List<BookVO> getSearchListWithPaging(Criteria criteria);
	
	public BookVO getOne(Long bno);
	
	public BookVO getBook(String isbn);

	public int insert(BookVO bookVO);
	
	public int getTotalCount(Criteria criteria);
	
	public int getTotalSearchCount(Criteria criteria);

	public int delete(Long bno);
}
