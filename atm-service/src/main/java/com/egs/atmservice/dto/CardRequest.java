package com.egs.atmservice.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

	private Long cardNumber;
	private Long cvv2;
	private Date expireDate;
	private Long accountId;

}
