package com.egs.bankservice.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;

import com.egs.bankservice.common.util.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

	@NotNull(message = ErrorMessage.CARD_NUMBER_IS_MANDATORY)
	private Long cardNumber;
	@NotNull(message = ErrorMessage.CVV2_IS_MANDATORY)
	private Long cvv2;
	@NotNull(message = ErrorMessage.EXPIRE_DATE_IS_MANDATORY)
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;
	@NotNull(message = ErrorMessage.ACCOUNT_IS_MANDATORY)
	private Long accountId;

}
