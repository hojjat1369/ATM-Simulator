package com.egs.bankservice.common.util;


import org.springframework.stereotype.Component;


@Component
public class ErrorMessage {

	public static final String NAME_IS_MANDATORY = "Name is mandatory!";
	public static final String LAST_NAME_IS_MANDATORY = "Last name is mandatory!";
	public static final String BALANCE_IS_MANDATORY = "Balance is mandatory!";
	public static final String AUTHENTICATION_TYPE_IS_MANDATORY = "Authentication type is mandatory!";
	public static final String AUTHENTICATION_VALUE_IS_MANDATORY = "Authentication value is mandatory!";
	public static final String ACCOUNT_NOT_FOUND = "Account not found!";
	public static final String CARD_NOT_FOUND = "Card not found!";
	public static final String INVALID_CARD = "Card is invalid!";
	public static final String EXPIRED_CARD = "Card is expired!";
	public static final String INVALID_PIN = "Pin is invalid!";
	public static final String INVALID_FINGERPRINT = "Fingerprint is not correct!";
	public static final String INSUFFICIENT_BALANCE = "Insufficient Balance!";

}
