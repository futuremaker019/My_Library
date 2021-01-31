package com.demo.domain;

import lombok.Data;

@Data
public class Email {

	private String mailFrom;
	private String mailTo;
	private String mailCC;
	private String mailBcc;
	private String mailSubject;
	private String mailContent;
	private String contentType;
	
	public Email() {
		contentType = "text/plain";
	}
}
