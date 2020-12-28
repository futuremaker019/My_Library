package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
@ControllerAdvice
public class ExceptionAdvice {

	// security access handler와 충돌, 수정 요망
//	@ExceptionHandler(Exception.class) 
//	public void getExcepttionPage(Exception ex, Model model) {
//		log.error("==========================");
//		log.error("Exception occured : " + ex.getMessage());
//	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noPageFound(NoHandlerFoundException ex) {
		return "/exception/404";
	}
}
 