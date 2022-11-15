package com.egs.atmservice.common.util;


import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.egs.atmservice.common.exception.BankServiceException;
import com.egs.atmservice.common.exception.DomainException;
import com.egs.atmservice.dto.AccountResponse;
import com.egs.atmservice.dto.AuthenticateCardRequest;
import com.egs.atmservice.dto.BalanceRequest;
import com.egs.atmservice.dto.CheckCardRequest;
import com.egs.atmservice.dto.CheckCardResponse;
import com.egs.atmservice.dto.DepositRequest;
import com.egs.atmservice.dto.WithdrawRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class BankServiceGatewayImpl {

	private final RestTemplate restTemplate;
	private static final String BASE_URL = "http://localhost:8081/api";

	@CircuitBreaker(name = "atmService", fallbackMethod = "bankServiceFailed")
	public CheckCardResponse checkCard(CheckCardRequest request) throws DomainException {

		return restTemplate.postForObject(getCheckCardUrl(), request, CheckCardResponse.class);
	}

	@CircuitBreaker(name = "atmService", fallbackMethod = "accountServiceFailed")
	public AccountResponse authenticateCard(AuthenticateCardRequest request) throws DomainException {

		return restTemplate.postForObject(getAuthenticateCardUrl(), request, AccountResponse.class);
	}

	@CircuitBreaker(name = "atmService", fallbackMethod = "accountServiceFailed")
	public AccountResponse withdraw(WithdrawRequest request) throws DomainException {

		return restTemplate.postForObject(getWithdrawUrl(), request, AccountResponse.class);
	}

	@CircuitBreaker(name = "atmService", fallbackMethod = "accountServiceFailed")
	public AccountResponse deposit(DepositRequest request) throws DomainException {

		return restTemplate.postForObject(getDepositUrl(), request, AccountResponse.class);
	}

	@CircuitBreaker(name = "atmService", fallbackMethod = "accountServiceFailed")
	public AccountResponse balance(BalanceRequest request) throws DomainException {

		return restTemplate.postForObject(getBalanceUrl(), request, AccountResponse.class);
	}

	public CheckCardResponse bankServiceFailed(ResourceAccessException e) throws BankServiceException {

		throw new BankServiceException();
	}

	public CheckCardResponse bankServiceFailed(HttpClientErrorException e) throws DomainException {

		throw new DomainException(e.getMessage());
	}

	public CheckCardResponse bankServiceFailed(Exception e) throws DomainException {

		throw new DomainException(e.getMessage());
	}

	public AccountResponse accountServiceFailed(ResourceAccessException e) throws BankServiceException {

		throw new BankServiceException();
	}

	public AccountResponse accountServiceFailed(HttpClientErrorException e) throws DomainException {

		throw new DomainException(e.getMessage());
	}

	public AccountResponse accountServiceFailed(Exception e) throws DomainException {

		throw new DomainException(e.getMessage());
	}

	private String getCheckCardUrl() {

		return BASE_URL + "/card/check";
	}

	private String getAuthenticateCardUrl() {

		return BASE_URL + "/card/authenticate";
	}

	private String getWithdrawUrl() {

		return BASE_URL + "/account/withdraw";
	}

	private String getDepositUrl() {

		return BASE_URL + "/account/deposit";
	}

	private String getBalanceUrl() {

		return BASE_URL + "/account/balance";
	}

}
