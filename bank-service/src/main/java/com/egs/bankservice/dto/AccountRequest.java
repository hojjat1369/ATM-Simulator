package com.egs.bankservice.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.egs.bankservice.common.dto.CommonRequest;
import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.ErrorMessage;
import com.egs.bankservice.common.util.ObjectUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest extends CommonRequest {

	@NotBlank(message = ErrorMessage.NAME_IS_MANDATORY)
	private String name;
	@NotBlank(message = ErrorMessage.LAST_NAME_IS_MANDATORY)
	private String lastName;
	@NotNull(message = ErrorMessage.BALANCE_IS_MANDATORY)
	private Long balance;
	@NotNull(message = ErrorMessage.AUTHENTICATION_TYPE_IS_MANDATORY)
	private AuthMethodRequest authRequest;

	@Override
	public void validate() throws DomainException {

		ObjectUtil.notNull(name, ErrorMessage.NAME_IS_MANDATORY);
		ObjectUtil.notNull(lastName, ErrorMessage.LAST_NAME_IS_MANDATORY);
		ObjectUtil.notNull(lastName, ErrorMessage.AUTHENTICATION_TYPE_IS_MANDATORY);
	}

}
