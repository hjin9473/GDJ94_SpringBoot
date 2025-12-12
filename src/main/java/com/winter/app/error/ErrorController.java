package com.winter.app.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//예외를 처리하는 전역 처리하는 Controller
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(NullPointerException.class)
	public String e1(Model model) {
		return "error/error_page";
	}
		
	@ExceptionHandler(Exception.class)
	public String exc2(Model model) {
		
		return "error/error_page";
	}
	@ExceptionHandler(Throwable.class)
	public String exc3(Model model) {
		
		return "error/error_page";
	}
}