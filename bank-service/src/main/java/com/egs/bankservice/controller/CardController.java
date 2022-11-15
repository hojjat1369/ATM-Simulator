package com.egs.bankservice.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.egs.bankservice.common.aspect.Logging;
import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.dto.AuthenticateCardRequest;
import com.egs.bankservice.dto.CardRequest;
import com.egs.bankservice.dto.CardResponse;
import com.egs.bankservice.dto.CheckCardRequest;
import com.egs.bankservice.dto.CheckCardResponse;
import com.egs.bankservice.service.AccountService;
import com.egs.bankservice.service.CardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;
	private final AccountService accountService;

	@Logging
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public CardResponse createCard(@Valid @RequestBody CardRequest cardRequest) throws DomainException {

		return cardService.createCard(cardRequest, accountService.getAccount(cardRequest.getAccountId()));
	}

	@Logging
	@PostMapping("/check")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CheckCardResponse checkCard(@Valid @RequestBody CheckCardRequest checkCardRequest) throws DomainException {

		return cardService.checkCard(checkCardRequest);
	}

	@Logging
	@PostMapping("/authenticate")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CheckCardResponse authenticateCard(@Valid @RequestBody AuthenticateCardRequest authRequest) throws DomainException {

		return cardService.authenticateCard(authRequest);
	}
}
