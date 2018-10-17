package com.rest.calculatorws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.rest.utils.SOAPConnector;

/**
 * The Class CalculatorConfig.
 */
@Configuration
public class CalculatorConfig {

	/**
	 * Marshaller.
	 *
	 * @return the jaxb 2 marshaller
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

		marshaller.setContextPath("org.example.echo.service.skeleton");
		return marshaller;
	}

	/**
	 * Adds the client.
	 *
	 * @param marshaller
	 *            the marshaller
	 * @return the SOAP connector
	 */
	@Bean
	public SOAPConnector addClient(Jaxb2Marshaller marshaller) {
		SOAPConnector client = new SOAPConnector();
		client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
