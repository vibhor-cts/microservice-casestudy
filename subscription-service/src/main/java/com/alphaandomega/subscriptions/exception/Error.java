package com.alphaandomega.subscriptions.exception;

public class Error {
	private String code;
	private String message;

	public Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
