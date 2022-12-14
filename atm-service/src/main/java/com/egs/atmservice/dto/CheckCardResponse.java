package com.egs.atmservice.dto;


import java.util.Date;

import com.egs.atmservice.enums.AuthenticationType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckCardResponse {

	private Long cardNumber;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;
	private Long accountId;
	private AuthenticationType authenticationType;

}
