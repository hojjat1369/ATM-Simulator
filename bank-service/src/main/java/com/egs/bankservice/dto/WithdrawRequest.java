package com.egs.bankservice.dto;


import javax.validation.constraints.NotNull;

import com.egs.bankservice.common.util.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

	@NotNull(message = ErrorMessage.AMOUNT_MANDATORY)
	private Long amount;
	@NotNull(message = ErrorMessage.CARD_NUMBER_IS_MANDATORY)
	private Long cardNumber;

}
