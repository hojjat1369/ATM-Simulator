package com.egs.atmservice.dto;


import com.egs.atmservice.enums.AuthenticationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

	private Long cardNumber;
	private Long balance;
	private Long accountId;
	private AuthenticationType authenticationType;

}
