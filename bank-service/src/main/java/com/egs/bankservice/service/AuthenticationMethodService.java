package com.egs.bankservice.service;


import org.springframework.stereotype.Service;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.dto.AuthMethodRequest;
import com.egs.bankservice.dto.AuthenticateCardRequest;
import com.egs.bankservice.entity.AuthenticationMethod;
import com.egs.bankservice.enums.AuthenticationType;
import com.egs.bankservice.repository.AuthenticationMethodRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationMethodService {

	private final AuthenticationMethodRepository repository;

	public AuthenticationMethod createAuthMethod(AuthMethodRequest request) {

		AuthenticationMethod authenticationMethod = AuthenticationMethod.builder().authType(request.getAuthType()).authValue(request.getAuthValue()).build();
		repository.save(authenticationMethod);
		log.info("authentication method {} is saved.", authenticationMethod.getId());
		return authenticationMethod;
	}

	public void authnticate(AuthenticationMethod method, AuthenticateCardRequest request) throws DomainException {

		if (!method.getAuthValue().equals(request.getAuthenticationValue())){
			throw new DomainException(getErrorMessage(method.getAuthType()));
		}
	}

	private String getErrorMessage(AuthenticationType type) {

		if (AuthenticationType.FINGERPRINT.equals(type)){
			return ErrorMessage.INVALID_FINGERPRINT;
		}
		return ErrorMessage.INVALID_PIN;
	}

}
