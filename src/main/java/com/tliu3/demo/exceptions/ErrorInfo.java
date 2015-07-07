package com.tliu3.demo.exceptions;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "timestamp", "status", "error", "exception", "message", "path", "query" })
public class ErrorInfo {
	public ZonedDateTime getTimestamp() {
		return this.timestamp;
	}

	public int getStatus() {
		return this.httpStatus.value();
	}

	public String getError() {
		return this.httpStatus.getReasonPhrase();
	}

	public String getException() {
		return this.cause == null ? null : this.cause.getClass().getName();
	}

	public String getMessage() {
		return this.cause == null ? null : this.cause.getMessage();
	}

	public String getPath() {
		return this.request.getRequestURI();
	}

	public String getQuery() {
		return this.request.getQueryString();
	}

	@JsonIgnore
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public ErrorInfo(Throwable cause, HttpServletRequest request) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, cause, request);
	}

	public ErrorInfo(HttpStatus httpStatus, Throwable cause, HttpServletRequest request) {
		this.httpStatus = httpStatus;
		this.cause = cause;
		this.request = request;
	}

	private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneOffset.UTC);
	private final HttpStatus httpStatus;
	private final Throwable cause;
	private final HttpServletRequest request;
}
