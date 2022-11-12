package com.egs.atmservice.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.egs.atmservice.common.aspect.Logging;
import com.egs.atmservice.common.exception.AuthenticateException;
import com.egs.atmservice.common.util.BankServiceGatewayImpl;
import com.egs.atmservice.common.util.ErrorMessage;
import com.egs.atmservice.dto.AuthenticateCardRequest;
import com.egs.atmservice.dto.BalanceRequest;
import com.egs.atmservice.dto.BalanceResponse;
import com.egs.atmservice.dto.Card;
import com.egs.atmservice.dto.CheckCardRequest;
import com.egs.atmservice.dto.DepositRequest;
import com.egs.atmservice.dto.DepositResponse;
import com.egs.atmservice.dto.WithdrawRequest;
import com.egs.atmservice.dto.WithdrawResponse;
import com.egs.atmservice.enums.CardAuthState;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/atm/card")
@RequiredArgsConstructor
public class CardController {

	private final BankServiceGatewayImpl bankService;

	@Logging
	@PostMapping("/verify")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Card verifyCard(@Valid @RequestBody CheckCardRequest checkCardRequest, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Card checkCard = bankService.checkCard(checkCardRequest);
		session.setAttribute(String.valueOf(checkCardRequest.getCardNumber()), CardAuthState.VALID);
		return checkCard;
	}

	@Logging
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Card login(@Valid @RequestBody AuthenticateCardRequest authenticateCardRequest, HttpServletRequest request) throws AuthenticateException {

		HttpSession session = request.getSession();
		if (!CardAuthState.VALID.equals(session.getAttribute(String.valueOf(authenticateCardRequest.getCardNumber()))))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
		Card card = bankService.authenticateCard(authenticateCardRequest);
		session.setAttribute(String.valueOf(authenticateCardRequest.getCardNumber()), CardAuthState.AUTHENTICATED);
		return card;
	}

	@Logging
	@PostMapping("/withdraw")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public WithdrawResponse withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest, HttpServletRequest request) throws AuthenticateException {

		HttpSession session = request.getSession();
		if (!CardAuthState.AUTHENTICATED.equals(session.getAttribute(String.valueOf(withdrawRequest.getCardNumber()))))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
		return bankService.withdraw(withdrawRequest);
	}

	@Logging
	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public DepositResponse deposit(@Valid @RequestBody DepositRequest depositRequest, HttpServletRequest request) throws AuthenticateException {

		HttpSession session = request.getSession();
		if (!CardAuthState.AUTHENTICATED.equals(session.getAttribute(String.valueOf(depositRequest.getCardNumber()))))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
		return bankService.deposit(depositRequest);
	}

	@Logging
	@PostMapping("/balance")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BalanceResponse balance(@Valid @RequestBody BalanceRequest balanceRequest, HttpServletRequest request) throws AuthenticateException {

		HttpSession session = request.getSession();
		if (!CardAuthState.AUTHENTICATED.equals(session.getAttribute(String.valueOf(balanceRequest.getCardNumber()))))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
		return bankService.balance(balanceRequest);
	}

}
