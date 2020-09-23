package com.demo.service;

import java.util.List;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookService {

	public void register(BookVO bookVO);
	
	public List<BookVO> getListWithPaging(Criteria cri);
	
	public int getTotal(Criteria criteria);
}
