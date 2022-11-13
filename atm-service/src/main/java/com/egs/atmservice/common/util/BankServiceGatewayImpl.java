package com.egs.atmservice.common.util;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.egs.atmservice.common.exception.DomainException;
import com.egs.atmservice.dto.AuthenticateCardRequest;
import com.egs.atmservice.dto.BalanceRequest;
import com.egs.atmservice.dto.BalanceResponse;
import com.egs.atmservice.dto.Card;
import com.egs.atmservice.dto.CheckCardRequest;
import com.egs.atmservice.dto.DepositRequest;
import com.egs.atmservice.dto.DepositResponse;
import com.egs.atmservice.dto.WithdrawRequest;
import com.egs.atmservice.dto.WithdrawResponse;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class BankServiceGatewayImpl {

	private final RestTemplate restTemplate;
	private static final String BASE_URL = "http://localhost:8081/api";

	public Card checkCard(CheckCardRequest request) {

		return restTemplate.postForObject(getCheckCardUrl(), request, Card.class);
	}

	public Card authenticateCard(AuthenticateCardRequest request) {

		return restTemplate.postForObject(getAuthenticateCardUrl(), request, Card.class);
	}

	public WithdrawResponse withdraw(WithdrawRequest request) {

		return restTemplate.postForObject(getWithdrawUrl(), request, WithdrawResponse.class);
	}

	public DepositResponse deposit(DepositRequest request) {

		return restTemplate.postForObject(getDepositUrl(), request, DepositResponse.class);
	}

	public BalanceResponse balance(BalanceRequest request) {

		return restTemplate.postForObject(getBalanceUrl(), request, BalanceResponse.class);
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

	public void failed() throws DomainException {

		throw new DomainException(ErrorMessage.BANK_ID_DOWN);
	}
}
