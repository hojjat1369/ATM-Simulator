package com.egs.atmservice.dto;


import com.egs.atmservice.common.dto.CommonRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateCardRequest extends CommonRequest {

	private Long cardNumber;
	private String authenticationValue;

}
