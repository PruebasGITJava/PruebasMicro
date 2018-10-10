package com.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.rest.utils.SOAPConnector;

@Configuration
public class Config {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

		marshaller.setContextPath("org.example.echo.service.skeleton");
		return marshaller;
	}

	@Bean
	public SOAPConnector addClient(Jaxb2Marshaller marshaller) {
		SOAPConnector client = new SOAPConnector();
		client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
