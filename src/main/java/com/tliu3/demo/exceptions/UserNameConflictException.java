package com.tliu3.demo.exceptions;

public class UserNameConflictException extends ConflictException {
	private static final long serialVersionUID = -3459740674809695124L;

	public UserNameConflictException(String name) {
		super("User [" + name + "] already exists.");
	}
}
