package com.egs.bankservice.service;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.dto.AccountRequest;
import com.egs.bankservice.dto.AccountResponse;
import com.egs.bankservice.dto.BalanceRequest;
import com.egs.bankservice.dto.DepositRequest;
import com.egs.bankservice.dto.WithdrawRequest;
import com.egs.bankservice.entity.Account;
import com.egs.bankservice.entity.AuthenticationMethod;
import com.egs.bankservice.entity.Card;
import com.egs.bankservice.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

	private final AccountRepository repository;
	private final AuthenticationMethodService autMethodService;
	private final CardService cardService;

	public AccountResponse createAccount(@Valid AccountRequest request) {

		AuthenticationMethod preferredAuthMethod = autMethodService.createAuthMethod(request.getAuthRequest());
		Account account = Account.builder().lastName(request.getLastName()).name(request.getName()).balance(request.getBalance()).authMethod(preferredAuthMethod).build();
		repository.save(account);
		log.info("account {} is saved.", account.getId());
		return AccountResponse.builder().balance(account.getBalance()).accountId(account.getId()).build();
	}

	public Account getAccount(Long id) throws DomainException {

		return repository.findById(id).orElseThrow(() -> new DomainException(ErrorMessage.ACCOUNT_NOT_FOUND));
	}

	public List<AccountResponse> getAccounts() {

		List<Account> accounts = repository.findAll();
		return accounts.stream().map(this::mapToAccountResponse).collect(Collectors.toList());
	}

	public AccountResponse withdraw(WithdrawRequest withdrawRequest) throws DomainException {

		Card card = cardService.getCardByCardNumber(withdrawRequest.getCardNumber());
		Account account = card.getAccount();
		Long balance = account.getBalance();
		if (balance >= withdrawRequest.getAmount()){
			balance = balance - withdrawRequest.getAmount();
			account.setBalance(balance);
			repository.save(account);
			return AccountResponse.builder().balance(account.getBalance()).accountId(account.getId()).cardNumber(card.getCardNumber()).build();
		}
		throw new DomainException(ErrorMessage.INSUFFICIENT_BALANCE);
	}

	public AccountResponse deposit(DepositRequest depositRequest) throws DomainException {

		Card card = cardService.getCardByCardNumber(depositRequest.getCardNumber());
		Account account = card.getAccount();
		account.setBalance(account.getBalance() + depositRequest.getAmount());
		repository.save(account);
		return AccountResponse.builder().balance(account.getBalance()).accountId(account.getId()).cardNumber(card.getCardNumber()).build();
	}

	public AccountResponse getBalance(BalanceRequest balanceRequest) throws DomainException {

		Card card = cardService.getCardByCardNumber(balanceRequest.getCardNumber());
		Account account = card.getAccount();
		return AccountResponse.builder().balance(account.getBalance()).accountId(account.getId()).cardNumber(card.getCardNumber()).build();
	}

	private AccountResponse mapToAccountResponse(Account account) {

		return AccountResponse.builder().balance(account.getBalance()).build();
	}
}
