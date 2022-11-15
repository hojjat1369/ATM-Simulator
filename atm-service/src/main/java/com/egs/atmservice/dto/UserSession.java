package com.egs.atmservice.dto;


import java.io.Serializable;

import com.egs.atmservice.enums.CardAuthState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

	private static final long serialVersionUID = -8471902716978505544L;
	private Long cardNumber;
	private CardAuthState state;
}
