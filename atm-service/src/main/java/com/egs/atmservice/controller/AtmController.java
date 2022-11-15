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
import com.egs.atmservice.dto.AccountResponse;
import com.egs.atmservice.dto.AuthenticateCardRequest;
import com.egs.atmservice.dto.BalanceRequest;
import com.egs.atmservice.dto.CheckCardRequest;
import com.egs.atmservice.dto.CheckCardResponse;
import com.egs.atmservice.dto.DepositRequest;
import com.egs.atmservice.dto.UserSession;
import com.egs.atmservice.dto.WithdrawRequest;
import com.egs.atmservice.enums.CardAuthState;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/atm/card")
@RequiredArgsConstructor
public class AtmController {

	private final BankServiceGatewayImpl bankService;

	@Logging
	@PostMapping("/verify")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CheckCardResponse verifyCard(@Valid @RequestBody CheckCardRequest checkCardRequest, HttpServletRequest request) {

		CheckCardResponse checkCardResponse = bankService.checkCard(checkCardRequest);
		initializeSession(request, checkCardResponse.getCardNumber());
		return checkCardResponse;
	}

	@Logging
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse login(@Valid @RequestBody AuthenticateCardRequest authenticateCardRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSession = getUserSessionInfo(request.getSession(), authenticateCardRequest.getCardNumber());
		checkVerifiedCard(userSession);
		AccountResponse accountResponse = bankService.authenticateCard(authenticateCardRequest);
		authenticateSession(request, userSession);
		return accountResponse;
	}

	@Logging
	@PostMapping("/withdraw")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), withdrawRequest.getCardNumber());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.withdraw(withdrawRequest);
	}

	@Logging
	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse deposit(@Valid @RequestBody DepositRequest depositRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), depositRequest.getCardNumber());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.deposit(depositRequest);
	}

	@Logging
	@PostMapping("/balance")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse balance(@Valid @RequestBody BalanceRequest balanceRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), balanceRequest.getCardNumber());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.balance(balanceRequest);
	}

	private UserSession getUserSessionInstance(Long cardNumber) {

		return UserSession.builder().cardNumber(cardNumber).state(CardAuthState.VALID).build();
	}

	private UserSession getUserSessionInfo(HttpSession session, Long cardNumber) {

		Object userSession = session.getAttribute(String.valueOf(cardNumber));
		if (userSession != null)
			return (UserSession) userSession;
		return null;
	}

	private void checkVerifiedCard(UserSession userSession) throws AuthenticateException {

		if (userSession == null || !CardAuthState.VALID.equals(userSession.getState()))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
	}

	private void checkAuthnticatedCard(UserSession userSession) throws AuthenticateException {

		if (userSession == null || !CardAuthState.AUTHENTICATED.equals(userSession.getState()))
			throw new AuthenticateException(ErrorMessage.INVALID_CARD);
	}

	private void initializeSession(HttpServletRequest request, Long cardNumber) {

		UserSession userSession = getUserSessionInstance(cardNumber);
		request.getSession().setAttribute(String.valueOf(cardNumber), userSession);
	}

	private void authenticateSession(HttpServletRequest request, UserSession userSession) {

		userSession.setState(CardAuthState.AUTHENTICATED);
		request.getSession().setAttribute(String.valueOf(userSession.getCardNumber()), userSession);
	}

}
