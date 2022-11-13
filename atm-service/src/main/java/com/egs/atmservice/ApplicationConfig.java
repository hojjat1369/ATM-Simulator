package com.egs.atmservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.egs.atmservice.common.util.JwtTokenUtil;


@Configuration
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

	@Bean
	public JwtTokenUtil jwtTokenUtil() {

		return new JwtTokenUtil();
	}

}
