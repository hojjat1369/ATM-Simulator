package com.egs.bankservice.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.egs.bankservice.common.entity.AbstractEntity;
import com.egs.bankservice.enums.AuthenticationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "egs_bank_auth_method")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationMethod extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "authentication_type")
	private AuthenticationType authType;
	private String authValue;

}
