package com.tliu3.demo.controllers;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tliu3.demo.exceptions.ErrorInfo;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandlerController {
	private static final String LOG_MESSAGE_TEMPLATE = "[{}]: [{}]. Request path [{}], query string [{}].";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity typeMismatchException(HttpServletRequest request, TypeMismatchException e) {
		this.logErrorWithoutStackTrace(request, e);
		ErrorInfo errorInfo = new ErrorInfo(BAD_REQUEST, e, request);
		return status(errorInfo.getHttpStatus()).body(errorInfo);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity handler(HttpServletRequest request, Exception e) {
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
		if (responseStatus == null) {
			this.logError(request, e);
		} else {
			this.logWarning(request, e);
		}
		ErrorInfo errorInfo = responseStatus == null ? new ErrorInfo(e, request) : new ErrorInfo(responseStatus.value(), e, request);
		return status(errorInfo.getHttpStatus()).body(errorInfo);
	}

	private <T extends Throwable> void logError(HttpServletRequest request, T e) {
		logger.error(LOG_MESSAGE_TEMPLATE,
				e.getClass().getName(), e.getLocalizedMessage(), request.getRequestURI(), request.getQueryString(), e);
	}

	private <T extends Throwable> void logErrorWithoutStackTrace(HttpServletRequest request, T e) {
		logger.error(LOG_MESSAGE_TEMPLATE,
				e.getClass().getName(), e.getLocalizedMessage(), request.getRequestURI(), request.getQueryString());
	}

	private <T extends Throwable> void logWarning(HttpServletRequest request, T e) {
		logger.warn(LOG_MESSAGE_TEMPLATE,
				e.getClass().getName(), e.getLocalizedMessage(), request.getRequestURI(), request.getQueryString());
	}
}
