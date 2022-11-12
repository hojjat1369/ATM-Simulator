package com.egs.atmservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositResponse {

	private Long balance;
	private Long depositedAmount;
	private Long accountId;
	private Long cardNumber;

}
