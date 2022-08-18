package com.exceptionHandlingExample.demo.customeexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(){
		super();
	}
	public UserNotFoundException(String message) {
		super(message);
	}
}
