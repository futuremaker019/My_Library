package com.demo.mapper;

import java.util.List;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookMapper {
	
//	public List<BookVO> getList();
	
	public List<BookVO> getListWithPaging(Criteria cri);

	public int insert(BookVO bookVO);
	
	public int getTotalCount(Criteria criteria);
}
