package com.demo.mapper;

import java.util.List;

import com.demo.domain.Author;

public interface AuthorMapper {
	
	public List<Author> getAuthorList();

	public void insert(Author authorVO);
}
