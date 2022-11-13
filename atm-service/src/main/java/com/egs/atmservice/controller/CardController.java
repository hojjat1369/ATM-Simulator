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
import com.egs.atmservice.common.util.JwtTokenUtil;
import com.egs.atmservice.dto.AccountRequest;
import com.egs.atmservice.dto.AuthenticateCardRequest;
import com.egs.atmservice.dto.BalanceRequest;
import com.egs.atmservice.dto.BalanceResponse;
import com.egs.atmservice.dto.Card;
import com.egs.atmservice.dto.CheckCardRequest;
import com.egs.atmservice.dto.DepositRequest;
import com.egs.atmservice.dto.DepositResponse;
import com.egs.atmservice.dto.UserSession;
import com.egs.atmservice.dto.WithdrawRequest;
import com.egs.atmservice.dto.WithdrawResponse;
import com.egs.atmservice.enums.CardAuthState;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/atm/card")
@RequiredArgsConstructor
public class CardController {

	private final BankServiceGatewayImpl bankService;

	private final JwtTokenUtil jwtTokenUtil;

	@Logging
	@PostMapping("/verify")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Card verifyCard(@Valid @RequestBody CheckCardRequest checkCardRequest, HttpServletRequest request) {

		Card card = bankService.checkCard(checkCardRequest);
		return initializeSession(request, card);
	}

	@Logging
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Card login(@Valid @RequestBody AuthenticateCardRequest authenticateCardRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSession = getUserSessionInfo(request.getSession(), authenticateCardRequest.getToken());
		checkVerifiedCard(userSession);
		Card card = bankService.authenticateCard(authenticateCardRequest);
		authenticateSession(request, userSession);
		return card;
	}

	@Logging
	@PostMapping("/withdraw")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public WithdrawResponse withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), withdrawRequest.getToken());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.withdraw(appentAccountId(withdrawRequest, userSessionInfo.getAccountId()));
	}

	@Logging
	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public DepositResponse deposit(@Valid @RequestBody DepositRequest depositRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), depositRequest.getToken());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.deposit(appentAccountId(depositRequest, userSessionInfo.getAccountId()));
	}

	@Logging
	@PostMapping("/balance")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BalanceResponse balance(@Valid @RequestBody BalanceRequest balanceRequest, HttpServletRequest request) throws AuthenticateException {

		UserSession userSessionInfo = getUserSessionInfo(request.getSession(), balanceRequest.getToken());
		checkAuthnticatedCard(userSessionInfo);
		return bankService.balance(appentAccountId(balanceRequest, userSessionInfo.getAccountId()));
	}

	private UserSession getUserSessionInstance(Long accountId, Long cardNumber, String token) {

		return UserSession.builder().accountId(accountId).cardNumber(cardNumber).state(CardAuthState.VALID).token(token).build();
	}

	private UserSession getUserSessionInfo(HttpSession session, String token) {

		Object userSession = session.getAttribute(token);
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

	private Card initializeSession(HttpServletRequest request, Card card) {

		String jwtToken = jwtTokenUtil.generateAccessToken(String.valueOf(card.getCardNumber()));
		request.getSession().setAttribute(jwtToken, getUserSessionInstance(card.getAccountId(), card.getCardNumber(), jwtToken));
		card.setToken(jwtToken);
		return card;
	}

	private void authenticateSession(HttpServletRequest request, UserSession userSession) {

		userSession.setState(CardAuthState.AUTHENTICATED);
		request.getSession().setAttribute(userSession.getToken(), userSession);
	}

	private <T extends AccountRequest> T appentAccountId(T t, Long accountId) {

		t.setAccountId(accountId);
		return t;
	}

}
