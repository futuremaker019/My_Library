package com.demo.service;

import java.util.List;

import com.demo.domain.AuthorVO;
import com.demo.domain.BookVO;

public interface ApiService {
	
	public void register(BookVO bookVO, List<AuthorVO> authors);

	public BookVO findBookByIsbn(String isbn);
}
