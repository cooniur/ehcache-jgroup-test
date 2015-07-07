package com.tliu3.demo.exceptions;

public class BookNotFoundException extends NotFoundException {
	public BookNotFoundException(Long id) {
		super("Book [" + id + "] not found.");
	}
}
