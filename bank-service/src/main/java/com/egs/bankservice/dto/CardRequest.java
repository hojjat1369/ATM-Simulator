package com.egs.bankservice.dto;


import java.util.Date;

import javax.validation.constraints.NotNull;

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

	@NotNull
	private Long cardNumber;
	@NotNull
	private Long cvv2;
	@NotNull
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;
	@NotNull
	private Long accountId;

}
