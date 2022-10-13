package com.rodrigo.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SuperHeroeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3355936009782194386L;

	public SuperHeroeNotFoundException(String message) {
		super(message);
	}
}
