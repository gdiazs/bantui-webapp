package com.gdiazs.bantui.register;

public class RegisterUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegisterUserException() {
		super();
	}

	public RegisterUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterUserException(String message) {
		super(message);
	}

	public RegisterUserException(Throwable cause) {
		super(cause);
	}

}
