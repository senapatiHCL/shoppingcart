package com.example.demo.exception;

public class DataBaseAccessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataBaseAccessException(String message) {
        super(message);
    }

    public DataBaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
