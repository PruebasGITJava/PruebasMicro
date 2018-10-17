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

/**
 * The Class SOAPConnector.
 */
public class SOAPConnector extends WebServiceGatewaySupport {

	/**
	 * Gets the account add.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the account add
	 */

	public AddResponse getAccountAdd(int a, int b) {

		Add request = new Add();
		request.setIntA(a);
		request.setIntB(b);

		return ((AddResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Add", request,
				new SoapActionCallback("http://tempuri.org/Add")));

	}

	/**
	 * Gets the account divide.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the account divide
	 */
	public DivideResponse getAccountDivide(int a, int b) {

		Divide request = new Divide();
		request.setIntA(a);
		request.setIntB(b);

		return ((DivideResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Divide", request,
				new SoapActionCallback("http://tempuri.org/Divide")));

	}

	/**
	 * Gets the account multiply.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the account multiply
	 */
	public MultiplyResponse getAccountMultiply(int a, int b) {

		Multiply request = new Multiply();
		request.setIntA(a);
		request.setIntB(b);

		return ((MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Multiply", request,
				new SoapActionCallback("http://tempuri.org/Multiply")));

	}

	/**
	 * Gets the account subtract.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the account subtract
	 */
	public SubtractResponse getAccountSubtract(int a, int b) {

		Subtract request = new Subtract();
		request.setIntA(a);
		request.setIntB(b);
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/pruebas
		return ((SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://www.dneonline.com/calculator.asmx?op=Subtract", request,
				new SoapActionCallback("http://tempuri.org/Subtract")));

	}
}