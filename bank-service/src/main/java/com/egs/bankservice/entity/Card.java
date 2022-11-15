package com.egs.bankservice.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.egs.bankservice.common.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "egs_bank_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends AbstractEntity {

	@Column(unique = true, nullable = false)
	private Long cardNumber;
	@Column(nullable = false)
	private Long cvv2;
	@Column(nullable = false)
	private Date expireDate;

	private Boolean blocked;
	@Column(name = "verify_error_count")
	private Integer verifyErrorCount;

	@OneToOne
	@JoinColumn(name = "account_fk")
	private Account account;

	public void incrementErrorCount() {

		if (verifyErrorCount == null){
			verifyErrorCount = 0;
		}
		verifyErrorCount = verifyErrorCount + 1;
	}

}
