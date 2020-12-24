package com.demo.mapper;

import java.util.List;

import com.demo.domain.Attachment;

public interface AttachmentMapper {

	public void insert(Attachment attachment);
	
	public Attachment findById(Long id);
	
	public List<Attachment> getAttachmentList(Long board_id);
	
	public void deleteById(Long id);
}
