package com.egs.bankservice.util;


import com.egs.bankservice.entity.Account;
import com.egs.bankservice.entity.AuthenticationMethod;
import com.egs.bankservice.enums.AuthenticationType;


public class TestUtil {

	public static Account getTestAccount() {

		return Account.builder().name("testName").lastName("testLastName").balance(10l).authMethod(getTestAuthenticationMethod()).build();
	}

	public static AuthenticationMethod getTestAuthenticationMethod() {

		return AuthenticationMethod.builder().authType(AuthenticationType.PIN).authValue("testAuthValue").build();
	}

}
