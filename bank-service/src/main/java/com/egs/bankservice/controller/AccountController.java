package com.egs.bankservice.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.egs.bankservice.common.aspect.Logging;
import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.dto.AccountRequest;
import com.egs.bankservice.dto.AccountResponse;
import com.egs.bankservice.dto.BalanceRequest;
import com.egs.bankservice.dto.DepositRequest;
import com.egs.bankservice.dto.WithdrawRequest;
import com.egs.bankservice.service.AccountService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@Logging
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AccountResponse createAccount(@Valid @RequestBody AccountRequest accountRequest) {

		return accountService.createAccount(accountRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<AccountResponse> getAccounts() {

		return accountService.getAccounts();
	}

	@Logging
	@PostMapping("/withdraw")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) throws DomainException {

		return accountService.withdraw(withdrawRequest);
	}

	@Logging
	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse deposit(@Valid @RequestBody DepositRequest depositRequest) throws DomainException {

		return accountService.deposit(depositRequest);
	}

	@Logging
	@PostMapping("/balance")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountResponse balance(@Valid @RequestBody BalanceRequest balanceRequest) throws DomainException {

		return accountService.getBalance(balanceRequest);
	}
}
