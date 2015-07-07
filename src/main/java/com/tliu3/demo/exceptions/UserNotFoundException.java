package com.tliu3.demo.exceptions;

public class UserNotFoundException extends NotFoundException {
	private static final long serialVersionUID = -3461638152667959893L;

	public UserNotFoundException(Long id) {
		super("User [" + id + "] not found.");
	}
}
