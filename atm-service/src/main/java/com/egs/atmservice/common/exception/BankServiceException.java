package com.egs.atmservice.common.exception;


public class BankServiceException extends Exception {

	private static final long serialVersionUID = 2395463316208183185L;

	public BankServiceException() {

		super();
	}

	public BankServiceException(String message) {

		super(message);
	}
}
