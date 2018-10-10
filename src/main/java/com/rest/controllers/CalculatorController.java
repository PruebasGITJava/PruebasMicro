package com.rest.controllers;

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

import com.rest.utils.SOAPConnector;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
	@Autowired
	private SOAPConnector soapConnector;

	@GetMapping("/add")
	public String addContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		AddResponse response = soapConnector.getAccountAdd(a, b);
		response.getAddResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getAddResult()).toString();

	}

	@GetMapping("/divide")
	public String divideContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		DivideResponse response = soapConnector.getAccountDivide(a, b);
		response.getDivideResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getDivideResult()).toString();

	}

	@GetMapping("/multiply")
	public String multiplyContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		MultiplyResponse response = soapConnector.getAccountMultiply(a, b);
		response.getMultiplyResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getMultiplyResult()).toString();

	}

	@GetMapping("/subtract")
	public String subtractContact(@RequestParam(required = true, name = "a") int a,
			@RequestParam(required = true, name = "b") int b) {

		SubtractResponse response = soapConnector.getAccountSubtract(a, b);
		response.getSubtractResult();

		return ResponseEntity.ok(HttpStatus.OK + " RESULTADO CORRECTO " + response.getSubtractResult()).toString();

	}

}
