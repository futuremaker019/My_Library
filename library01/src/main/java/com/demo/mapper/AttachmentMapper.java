package com.demo.mapper;

import com.demo.domain.Attachment;

public interface AttachmentMapper {

	public void insert(Attachment attachment);
	
	public Attachment findById(Long id);
}
