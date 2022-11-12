package com.egs.bankservice.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

	private Long id;
	private String name;
	private String lastName;
	private Date email;
	private Long balance;

}
