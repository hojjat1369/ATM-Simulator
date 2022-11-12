package com.egs.atmservice.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CardAuthState {

							VALID(1, "valid"),
							AUTHENTICATED(2, "authenticated"),

	;

	private final int id;
	private final String name;

}
