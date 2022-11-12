package com.egs.bankservice.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;


@Component
public class SecurityUtil {

	public static String getSHA512SecurePassword(String passwordToHash) {

		String generatedPassword = null;
		String saltProperty = "[B@2f4d3709";

		if (passwordToHash != null && !passwordToHash.isEmpty()){
			try{
				byte[] salt = saltProperty.getBytes();
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				md.update(salt);
				byte[] bytes = md.digest(passwordToHash.getBytes());
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bytes.length; i++){
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				generatedPassword = sb.toString();
			}
			catch (NoSuchAlgorithmException e){
//				logger.error("getSHA512SecurePassword() | error in hashing password using sha 512! with exception : ", e);
			}
		}
		return generatedPassword;
	}

}
