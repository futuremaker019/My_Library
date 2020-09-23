package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.domain.Criteria;
import com.demo.mapper.AuthorMapper;
import com.demo.mapper.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BookServiceImpl implements BookService{
	
	@Setter (onMethod_= @Autowired)
	private BookMapper bookMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AuthorMapper authorMapper;
	
//	@Override
//	public int register(BookVO bookVO) {
//		return bookMapper.insert(bookVO) ;
//	}

	@Override
	public void register(BookVO bookVO) {
		bookMapper.insert(bookVO);
	}

	@Override
	public List<BookVO> getListWithPaging(Criteria cri) {
		return bookMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria criteria) {
		return bookMapper.getTotalCount(criteria);
	}
}
