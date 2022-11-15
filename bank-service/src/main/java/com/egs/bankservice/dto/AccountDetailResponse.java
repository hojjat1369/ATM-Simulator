package com.egs.bankservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailResponse {

	private Long cardNumber;
	private Long balance;
	private Long accountId;

}
