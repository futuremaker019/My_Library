package com.demo.mapper;

import java.util.List;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookMapper {
	
//	public List<BookVO> getList();
	
	public List<BookVO> getListWithPaging(Criteria criteria);
	
	public List<BookVO> getSearchListWithPaging(Criteria criteria);

	public int insert(BookVO bookVO);
	
	public int getTotalCount(Criteria criteria);
	
	public int getTotalSearchCount(Criteria criteria);
}
