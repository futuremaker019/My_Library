package com.demo.mapper;

import java.util.List;

import com.demo.domain.Collection;

public interface CollectionMapper {

	public boolean save(Collection collection);
	
	public Collection findById(Long collection_id);
	
	public List<Collection> findAllByBookId(Long book_id);
	
	public int deleteById(Long collection_id);
	
	public boolean update(Collection collection);
}
