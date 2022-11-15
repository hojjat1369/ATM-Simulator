package com.egs.bankservice.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {

	private Long id;
	private Long cardNumber;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;
	private Long accountId;

}
