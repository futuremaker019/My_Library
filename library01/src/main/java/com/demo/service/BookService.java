package com.demo.service;

import java.util.List;

import com.demo.domain.BookVO;

public interface BookService {

	public void register(BookVO bookVO);
	
	public List<BookVO> getList();
}
