package com.egs.bankservice.dto;


import com.egs.bankservice.enums.AuthenticationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

	private Long cardNumber;
	private Long balance;
	private Long accountId;

	private AuthenticationType authenticationType;

}
