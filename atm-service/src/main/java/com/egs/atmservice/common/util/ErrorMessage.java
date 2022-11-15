package com.egs.atmservice.common.util;


import org.springframework.stereotype.Component;


@Component
public class ErrorMessage {

	public static final String INVALID_CARD = "Card is invalid!";
	public static final String BANK_ID_DOWN = "Bank is down!";
	public static final String INTERNAL_ERROR = "Internal Error!";
	public static final String UNAUTHORIZED_CARD = "Unauthorized Card!";

}
