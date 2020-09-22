package com.demo.service;

import java.util.List;

import com.demo.domain.AuthorVO;

public interface AuthorService {
	
	public List<AuthorVO> getAuthorList();

	public void register(List<AuthorVO> authors);
}
