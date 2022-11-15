package com.egs.bankservice.user;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.dto.AccountRequest;
import com.egs.bankservice.dto.AccountResponse;
import com.egs.bankservice.dto.AuthMethodRequest;
import com.egs.bankservice.entity.Account;
import com.egs.bankservice.entity.AuthenticationMethod;
import com.egs.bankservice.enums.AuthenticationType;
import com.egs.bankservice.repository.AccountRepository;
import com.egs.bankservice.service.AccountService;
import com.egs.bankservice.service.AuthenticationMethodService;


@SpringBootTest
@ActiveProfiles("jenkins")
@RunWith(SpringRunner.class)
public class CreateAccountTest {

	@InjectMocks
	private AccountService accountService;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private AuthenticationMethodService authMethodService;

	private AccountRequest request;
	private AuthMethodRequest authRequest;

	@Before
	public void setup() {

		MockitoAnnotations.openMocks(this.getClass());
		authRequest = AuthMethodRequest.builder().authType(AuthenticationType.PIN).authValue("testValue").build();
		request = AccountRequest.builder().authRequest(authRequest).balance(10l).name("testName").lastName("testLastName").build();
		Mockito.when(authMethodService.createAuthMethod(Mockito.any(AuthMethodRequest.class))).thenReturn(getAuthenticationMethod(request));
	}

	@Test
	public void ok() throws DomainException {

		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(getAccount(request));
		Mockito.when(authMethodService.createAuthMethod(Mockito.any(AuthMethodRequest.class))).thenReturn(getAuthenticationMethod(request));
		AccountResponse account = accountService.createAccount(request);
		Assert.assertNotNull(account);
		Assert.assertEquals(account.getBalance(), request.getBalance());

	}

	private Account getAccount(AccountRequest request) {

		return Account.builder().balance(request.getBalance()).lastName(request.getLastName()).name(request.getName()).authMethod(getAuthenticationMethod(request)).build();
	}

	private AuthenticationMethod getAuthenticationMethod(AccountRequest request) {

		return AuthenticationMethod.builder().authType(request.getAuthRequest().getAuthType()).authValue(request.getAuthRequest().getAuthValue()).build();
	}
}
