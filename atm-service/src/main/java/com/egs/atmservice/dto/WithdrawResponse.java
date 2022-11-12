package com.egs.atmservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawResponse {

	private Long balance;
	private Long withdrawnAmount;
	private Long cardNumber;

}
