package com.rest.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

	// CONF PARA SWAGGER2
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	// CONF PARA GMAIL
	@Bean
	public static Properties getMailSender() {

		Properties props = new Properties();
		// Usar autenticación mediante usuario y clave
		props.put("mail.smtp.auth", "true");
		// Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.starttls.enable", "true");
		// El servidor SMTP de Google
		props.put("mail.smtp.host", "smtp.gmail.com");
		// El puerto SMTP seguro de Google
		props.put("mail.smtp.port", "587");
		return props;

	}

}
