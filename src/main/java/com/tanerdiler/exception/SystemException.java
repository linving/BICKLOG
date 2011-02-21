package com.tanerdiler.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 3021102232425694342L;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}
