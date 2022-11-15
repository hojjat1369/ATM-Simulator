package com.egs.atmservice.common.util;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.egs.atmservice.common.dto.ErrorResponse;
import com.egs.atmservice.common.exception.AuthenticateException;
import com.egs.atmservice.common.exception.BankServiceException;
import com.egs.atmservice.common.exception.DomainException;


@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String , String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = AuthenticateException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponse handleException(AuthenticateException ex) {

		return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ErrorMessage.UNAUTHORIZED_CARD);
	}

	@ExceptionHandler(value = DomainException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(DomainException ex) {

		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = BankServiceException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody ErrorResponse handleException(BankServiceException ex) {

		return new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ErrorMessage.BANK_ID_DOWN);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody ErrorResponse handleException(Exception ex) {

		logger.error("", ex);
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_ERROR);
	}

}
