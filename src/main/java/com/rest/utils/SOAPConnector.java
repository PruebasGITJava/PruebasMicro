package com.rest.utils;

import org.example.echo.service.skeleton.Add;
import org.example.echo.service.skeleton.AddResponse;
import org.example.echo.service.skeleton.Divide;
import org.example.echo.service.skeleton.DivideResponse;
import org.example.echo.service.skeleton.Multiply;
import org.example.echo.service.skeleton.MultiplyResponse;
import org.example.echo.service.skeleton.Subtract;
import org.example.echo.service.skeleton.SubtractResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SOAPConnector extends WebServiceGatewaySupport {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */

	public AddResponse getAccountAdd(int a, int b) {

		Add request = new Add();
		request.setIntA(a);
		request.setIntB(b);

		AddResponse response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Add", request,
				new SoapActionCallback("http://tempuri.org/Add"));

		return response;
	}

	public DivideResponse getAccountDivide(int a, int b) {

		Divide request = new Divide();
		request.setIntA(a);
		request.setIntB(b);

		DivideResponse response = (DivideResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Divide", request,
				new SoapActionCallback("http://tempuri.org/Divide"));

		return response;
	}

	public MultiplyResponse getAccountMultiply(int a, int b) {

		Multiply request = new Multiply();
		request.setIntA(a);
		request.setIntB(b);

		MultiplyResponse response = (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Multiply", request,
				new SoapActionCallback("http://tempuri.org/Multiply"));

		return response;
	}

	public SubtractResponse getAccountSubtract(int a, int b) {

		Subtract request = new Subtract();
		request.setIntA(a);
		request.setIntB(b);

		SubtractResponse response = (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Subtract", request,
				new SoapActionCallback("http://tempuri.org/Subtract"));

		return response;
	}
}