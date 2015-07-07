package com.tliu3.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public abstract class ConflictException extends RuntimeException {
	private static final long serialVersionUID = -8550773805565703176L;

	protected ConflictException(String message) {
		super(message);
	}
}
