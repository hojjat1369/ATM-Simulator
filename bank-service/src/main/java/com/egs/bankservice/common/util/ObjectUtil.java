package com.egs.bankservice.common.util;


import org.springframework.stereotype.Component;

import com.egs.bankservice.common.exception.DomainException;


@Component
public class ObjectUtil {

	public static void notNull(Object obj, String message) throws DomainException {

		if (obj == null){
			throw new DomainException(message);
		}
	}
}
