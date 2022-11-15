package com.egs.atmservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateCardRequest {

	private Long cardNumber;
	private String authenticationValue;

}
