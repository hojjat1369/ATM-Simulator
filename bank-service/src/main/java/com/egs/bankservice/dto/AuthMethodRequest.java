package com.egs.bankservice.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.egs.bankservice.common.dto.CommonRequest;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.enums.AuthenticationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthMethodRequest extends CommonRequest {

	@NotNull(message = ErrorMessage.AUTHENTICATION_TYPE_IS_MANDATORY)
	private AuthenticationType authType;
	@NotBlank(message = ErrorMessage.AUTHENTICATION_VALUE_IS_MANDATORY)
	private String authValue;

}
