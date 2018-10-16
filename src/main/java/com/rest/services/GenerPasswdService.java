package com.rest.services;

import java.util.Random;

public class GenerPasswdService {
	private GenerPasswdService() {
		throw new IllegalStateException("Utility class");
	}

	public static final String NUMEROS = "0123456789";

	public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public static final String ESPECIALES = "ñÑ";

	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}

	public static String getPassword() {
		return getPassword(8);
	}

	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	public static String getPassword(String key, int length) {

		StringBuilder pswd = new StringBuilder();
		for (int i = 0; i < length; i++) {
			Random r = new Random();
			pswd.append(key.charAt((int) (r.nextInt(key.length()))));
		}

		return pswd.toString();
	}

}
