package com.egs.atmservice.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

	private Long cardNumber;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;

	private Long accountId;
	private Long id;

}