package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;
import com.demo.mapper.AuthorMapper;
import com.demo.mapper.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ApiServiceImpl implements ApiService{
	
	@Setter(onMethod_ = @Autowired)
	private BookMapper bookMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AuthorMapper authorMapper;

	@Override
	@Transactional
	public void register(BookVO bookVO, List<AuthorVO> authors) {
		if (bookMapper.insert(bookVO) == 1) {
			for (AuthorVO author : authors) {
				authorMapper.insert(author);
			}
		}
	}
}
