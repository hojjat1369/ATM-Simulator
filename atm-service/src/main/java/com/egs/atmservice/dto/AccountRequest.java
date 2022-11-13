package com.egs.atmservice.dto;


import com.egs.atmservice.common.dto.CommonRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AccountRequest extends CommonRequest {

	private Long accountId;

}
