package com.egs.bankservice.dto;


import java.util.Date;

import com.egs.bankservice.common.dto.CommonRequest;
import com.egs.bankservice.enums.AuthenticationType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckCardResponse extends CommonRequest {

	private static final long serialVersionUID = 471803407402512569L;
	private Long cardNumber;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date expireDate;
	private Long accountId;
	private AuthenticationType authenticationType;

}
