package com.egs.bankservice.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateCardRequest {

	@NotNull
	private Long cardNumber;
	@NotBlank
	private String authenticationValue;

}
