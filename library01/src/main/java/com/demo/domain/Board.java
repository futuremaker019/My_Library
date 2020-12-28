package com.demo.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {

	private Long board_id;
	private String title;
	private String content;
	private String writer;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	
	private int replyCount;
	
	List<Attachment> files;
	
	public void updateBoard(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
