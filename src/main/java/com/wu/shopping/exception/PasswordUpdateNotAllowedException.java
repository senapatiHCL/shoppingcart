package com.wu.shopping.exception;

public class PasswordUpdateNotAllowedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PasswordUpdateNotAllowedException(String message) {
        super(message);
    }

    public PasswordUpdateNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
