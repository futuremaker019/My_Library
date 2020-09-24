package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.AuthorVO;
import com.demo.mapper.AuthorMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AuthorServiceImpl{
	
	@Setter(onMethod_ = @Autowired)
	private AuthorMapper authorMapper;

//	@Override
//	public void register(List<AuthorVO> authors) {
//		for (AuthorVO authorVO : authors) {
//			authorMapper.insert(authorVO);
//		}
//	}
//
//	@Override
//	public List<AuthorVO> getAuthorList() {
//		return authorMapper.getAuthorList();
//	}
}
