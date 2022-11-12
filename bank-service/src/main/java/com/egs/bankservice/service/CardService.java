package com.egs.bankservice.service;


import java.util.Date;

import org.springframework.stereotype.Service;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.CardUtil;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.dto.AuthenticateCardRequest;
import com.egs.bankservice.dto.CardRequest;
import com.egs.bankservice.dto.CardResponse;
import com.egs.bankservice.dto.CheckCardRequest;
import com.egs.bankservice.entity.Account;
import com.egs.bankservice.entity.Card;
import com.egs.bankservice.repository.CardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

	private final CardRepository cardRepository;
	private final AccountService accountService;
	private final AuthenticationMethodService authenticationMethodService;

	public CardResponse createCard(CardRequest request) throws DomainException {

		Account account = accountService.getAccount(request.getAccountId());
		Card card = Card.builder().cardNumber(request.getCardNumber()).cvv2(request.getCvv2()).expireDate(request.getExpireDate()).account(account).build();
		cardRepository.save(card);
		log.info("card {} is saved.", card.getId());
		return CardResponse.builder().accountId(card.getAccount().getId()).cardNumber(card.getCardNumber()).expireDate(card.getExpireDate()).id(card.getId()).build();
	}

	public Card getCardById(Long id) throws DomainException {

		return cardRepository.findById(id).orElseThrow(() -> new DomainException(ErrorMessage.CARD_NOT_FOUND));
	}

	public CardResponse checkCard(CheckCardRequest checkCardRequest) throws DomainException {

		if (!CardUtil.isValid(checkCardRequest.getCardNumber())){
			throw new DomainException(ErrorMessage.INVALID_CARD);
		}
		Card card = getCardByCardNumber(checkCardRequest.getCardNumber());
		if (new Date().after(card.getExpireDate())){
			throw new DomainException(ErrorMessage.EXPIRED_CARD);
		}
		return CardResponse.builder().accountId(card.getAccount().getId()).cardNumber(card.getCardNumber()).expireDate(card.getExpireDate()).id(card.getId()).build();

	}

	public CardResponse authenticateCard(AuthenticateCardRequest authRequest) throws DomainException {

		Card card = getCardByCardNumber(authRequest.getCardNumber());
		authenticationMethodService.authnticate(card.getAccount().getAuthMethod(), authRequest);
		return CardResponse.builder().accountId(card.getAccount().getId()).cardNumber(card.getCardNumber()).expireDate(card.getExpireDate()).id(card.getId()).build();

	}

	public Card getCardByCardNumber(Long cardNumber) throws DomainException {

		Card foundCard = cardRepository.findByCardNumber(cardNumber);
		if (foundCard == null){
			throw new DomainException(ErrorMessage.CARD_NOT_FOUND);
		}
		return foundCard;
	}
}
