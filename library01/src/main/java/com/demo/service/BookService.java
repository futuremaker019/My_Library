package com.demo.service;

import java.util.List;

import com.demo.domain.BookVO;
import com.demo.domain.Criteria;

public interface BookService {

	public List<BookVO> getListWithPaging(Criteria criteria);
	
	public List<BookVO> getSearchListWithPaging(Criteria criteria);
	
	public BookVO getBook(Long bno);
	
	public int getTotal(Criteria criteria);
	
	public int getTotalSearchItem(Criteria criteria);
	
	public int remove(Long bno);

	public void removeBooks(List<Long> bnos);
}
