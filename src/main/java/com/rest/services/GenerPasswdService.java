package com.rest.services;

<<<<<<< HEAD
/**
 * The Class GenerPasswdService.
 */
=======
import java.util.Random;

>>>>>>> refs/remotes/origin/pruebas
public class GenerPasswdService {
<<<<<<< HEAD

	/** The Constant NUMEROS. */
=======
	private GenerPasswdService() {
		throw new IllegalStateException("Utility class");
	}

>>>>>>> refs/remotes/origin/pruebas
	public static final String NUMEROS = "0123456789";

	/** The Constant MAYUSCULAS. */
	public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/** The Constant MINUSCULAS. */
	public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	/** The Constant ESPECIALES. */
	public static final String ESPECIALES = "ñÑ";

	private GenerPasswdService() {

	}

	/**
	 * Gets the pin number.
	 *
	 * @return the pin number
	 */
	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public static String getPassword() {
		return getPassword(8);
	}

	/**
	 * Gets the password.
	 *
	 * @param length
	 *            the length
	 * @return the password
	 */
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	/**
	 * Gets the password.
	 *
	 * @param key
	 *            the key
	 * @param length
	 *            the length
	 * @return the password
	 */
	public static String getPassword(String key, int length) {

		StringBuilder pswd = new StringBuilder();
		for (int i = 0; i < length; i++) {
			Random r = new Random();
			pswd.append(key.charAt((int) (r.nextInt(key.length()))));
		}

		return pswd.toString();
	}

}
