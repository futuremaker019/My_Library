package com.demo.mapper;

import java.util.List;

import com.demo.domain.BookVO;

public interface BookMapper {
	
	public List<BookVO> getList();

	public int insert(BookVO bookVO);
}
