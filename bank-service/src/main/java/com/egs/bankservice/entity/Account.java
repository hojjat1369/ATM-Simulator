package com.egs.bankservice.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.egs.bankservice.common.entity.AbstractEntity;
import com.egs.bankservice.common.util.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "egs_bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends AbstractEntity {

	@NotBlank(message = ErrorMessage.NAME_IS_MANDATORY)
	private String name;
	@NotBlank(message = ErrorMessage.LAST_NAME_IS_MANDATORY)
	private String lastName;
	@NotNull(message = ErrorMessage.BALANCE_IS_MANDATORY)
	private Long balance;

	@OneToOne
	@JoinColumn(name = "auth_method_fk")
	@NotNull(message = ErrorMessage.AUTHENTICATION_TYPE_IS_MANDATORY)
	private AuthenticationMethod authMethod;
}
