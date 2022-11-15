package com.egs.atmservice;


import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(postPaths()).build().apiInfo(generateApiInfo());
	}

	private ApiInfo generateApiInfo() {

		return new ApiInfoBuilder().title("ATM simulator").version("1.0.0").license("Apache 2.0").licenseUrl("http://www.egs.org/licenses/LICENSE-2.0").build();
	}

	private Predicate<String> postPaths() {

		return regex("/api/.*");
	}
}
