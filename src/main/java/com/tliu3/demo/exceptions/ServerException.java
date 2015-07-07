package com.tliu3.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {
	public ServerException(Throwable e) {
		super(e == null ? "Unexpected exception." : e.getMessage(), e);
	}
}
