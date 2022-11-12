package com.egs.bankservice.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum AuthenticationType {

								PIN(1, "pin"),
								FINGERPRINT(2, "fingerprint"),

	;

	private final int id;
	private final String name;

}
