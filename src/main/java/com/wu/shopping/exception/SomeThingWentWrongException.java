package com.wu.shopping.exception;

public class SomeThingWentWrongException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SomeThingWentWrongException(String message) {
        super(message);
    }

    public SomeThingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }
}