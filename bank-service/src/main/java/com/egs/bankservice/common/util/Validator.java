package com.egs.bankservice.common.util;


import com.egs.bankservice.common.exception.DomainException;


public interface Validator {

	public void validate() throws DomainException;
}
