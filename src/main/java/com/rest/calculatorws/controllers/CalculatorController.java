package com.rest.calculatorws.controllers;

import org.example.echo.service.skeleton.AddResponse;
import org.example.echo.service.skeleton.DivideResponse;
import org.example.echo.service.skeleton.MultiplyResponse;
import org.example.echo.service.skeleton.SubtractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.constants.ConstantApp;
import com.rest.utils.SOAPConnector;

/**
 * The Class CalculatorController.
 */
@RestController
@RequestMapping(ConstantApp.CALCULATOR)
public class CalculatorController {

	/** The soap connector. */
	@Autowired
	private SOAPConnector soapConnector;

	/**
	 * Adds the contact.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the string
	 */
	@GetMapping(ConstantApp.ADDCALCULATOR)
	public String addContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		AddResponse response = soapConnector.getAccountAdd(a, b);
		response.getAddResult();

		return ResponseEntity.ok(HttpStatus.OK + ConstantApp.RESULT + response.getAddResult()).toString();

	}

	/**
	 * Divide contact.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the string
	 */
	@GetMapping(ConstantApp.DIVCALCULATOR)
	public String divideContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		DivideResponse response = soapConnector.getAccountDivide(a, b);
		response.getDivideResult();

		return ResponseEntity.ok(HttpStatus.OK + ConstantApp.RESULT + response.getDivideResult()).toString();

	}

	/**
	 * Multiply contact.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the string
	 */
	@GetMapping(ConstantApp.MULCALCULATOR)
	public String multiplyContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		MultiplyResponse response = soapConnector.getAccountMultiply(a, b);
		response.getMultiplyResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getMultiplyResult()).toString();

	}

	/**
	 * Subtract contact.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the string
	 */
	@GetMapping(ConstantApp.SUBCALCULATOR)
	public String subtractContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		SubtractResponse response = soapConnector.getAccountSubtract(a, b);
		response.getSubtractResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getSubtractResult()).toString();

	}

}
