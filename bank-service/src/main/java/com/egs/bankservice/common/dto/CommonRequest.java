package com.egs.bankservice.common.dto;


import java.io.Serializable;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.Validator;


public class CommonRequest implements Validator, Serializable {

	private static final long serialVersionUID = 6171931675706659370L;

	@Override
	public void validate() throws DomainException {

		return;
	}

}
