/**
 * 
 */
package com.userrestmanager.app.userrestmanager.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author PastoreGu
 *
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.userrestmanager.app.userrestmanager"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfoMetaData());
	}

	private ApiInfo apiInfoMetaData() {
		return new ApiInfoBuilder().title("USER MANAGER").description("API ENDPOINTS USED FOR USER MANAGEMENT").build();
	}

}
