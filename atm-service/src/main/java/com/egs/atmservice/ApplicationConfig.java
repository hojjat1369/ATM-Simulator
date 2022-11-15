package com.egs.atmservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

}
