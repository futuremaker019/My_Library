package com.demo.dto;

import com.demo.domain.Criteria;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean previous;
	private boolean next;
	
	private int total;
	private Criteria criteria;
	
	public PageDTO(Criteria criteria, int total) {
		
		this.criteria = criteria;
		this.total = total;
		
		this.endPage = (int) (Math.ceil(criteria.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEndPage = (int) Math.ceil(total * 1.0 / criteria.getAmount());
		
		if (realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.previous = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
