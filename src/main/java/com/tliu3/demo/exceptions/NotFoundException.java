package com.tliu3.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class NotFoundException extends RuntimeException {
	protected NotFoundException(String message) {
		super(message);
	}

	protected NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
