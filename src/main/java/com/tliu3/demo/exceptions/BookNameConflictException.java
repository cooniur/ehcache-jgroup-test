package com.tliu3.demo.exceptions;

public class BookNameConflictException extends ConflictException {
	private static final long serialVersionUID = 6884745042640652941L;

	public BookNameConflictException(String name) {
		super("Book [" + name + "] already exists.");
	}
}
