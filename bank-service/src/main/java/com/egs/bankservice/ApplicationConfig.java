package com.egs.bankservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.egs.bankservice.common.util.JwtTokenUtil;


@Configuration
public class ApplicationConfig {

	@Bean
	public JwtTokenUtil jwtTokenUtil() {

		return new JwtTokenUtil();
	}

}
