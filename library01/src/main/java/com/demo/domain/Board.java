package com.demo.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private Long board_id;
	private String title;
	private String content;
	private String writer;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	
	
}
