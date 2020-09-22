package com.demo.mapper;

import java.util.List;

import com.demo.domain.AuthorVO;

public interface AuthorMapper {
	
	public List<AuthorVO> getAuthorList();

	public void insert(AuthorVO authorVO);
}
