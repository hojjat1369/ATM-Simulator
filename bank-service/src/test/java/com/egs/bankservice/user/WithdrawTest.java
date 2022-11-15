package com.egs.bankservice.user;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.dto.AccountResponse;
import com.egs.bankservice.dto.WithdrawRequest;
import com.egs.bankservice.repository.AccountRepository;
import com.egs.bankservice.service.AccountService;
import com.egs.bankservice.service.CardService;
import com.egs.bankservice.util.TestUtil;


@SpringBootTest
@ActiveProfiles("jenkins")
@RunWith(SpringRunner.class)
public class WithdrawTest {

	@InjectMocks
	private AccountService accountService;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private CardService cardService;

	private WithdrawRequest request;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void setup() {

		MockitoAnnotations.openMocks(this.getClass());
		request = WithdrawRequest.builder().amount(5l).cardNumber(123456789l).build();
	}

	@Test
	public void ok() throws DomainException {

		Mockito.when(cardService.getCardByCardNumber(Mockito.any())).thenReturn(TestUtil.getTestCard());
		AccountResponse account = accountService.withdraw(request);
		Assert.assertNotNull(account);

	}

	@Test
	public void insufficientBalance() throws DomainException {

		exceptionRule.expectMessage(ErrorMessage.INSUFFICIENT_BALANCE);
		exceptionRule.expect(DomainException.class);

		request.setAmount(1000000l);
		Mockito.when(cardService.getCardByCardNumber(Mockito.any())).thenReturn(TestUtil.getTestCard());
		accountService.withdraw(request);

	}

}
