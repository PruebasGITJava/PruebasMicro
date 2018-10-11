package com.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

<<<<<<< HEAD
/**
 * The Class Contact.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	/** The nombre. */
	@Column(name = "nombre")
	private String nombre;

	/** The appellidos. */
	@Column(name = "appellidos")
	private String appellidos;

	/** The edad. */
	@Column(name = "edad")
	private int edad;

	/** The localidad. */
	@Column(name = "localidad")
	private String localidad;

	/** The email. */
	@Email
	@Column(name = "email")
	private String email;

	/** The passwd. */
	@Column(name = "passwd")
	private String passwd;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the appellidos.
	 *
	 * @return the appellidos
	 */
	public String getAppellidos() {
		return appellidos;
	}

	/**
	 * Sets the appellidos.
	 *
	 * @param appellidos
	 *            the appellidos to set
	 */
	public void setAppellidos(String appellidos) {
		this.appellidos = appellidos;
	}

	/**
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Sets the edad.
	 *
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the passwd.
	 *
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * Sets the passwd.
	 *
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * Instantiates a new contact.
	 *
	 * @param id
	 *            the id
	 * @param nombre
	 *            the nombre
	 * @param appellidos
	 *            the appellidos
	 * @param edad
	 *            the edad
	 * @param localidad
	 *            the localidad
	 * @param email
	 *            the email
	 * @param passwd
	 *            the passwd
	 */
	public Contact(int id, String nombre, String appellidos, int edad, String localidad, String email, String passwd) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.appellidos = appellidos;
		this.edad = edad;
		this.localidad = localidad;
		this.email = email;
		this.passwd = passwd;
	}

	/**
	 * Instantiates a new contact.
	 */
=======
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;
	@Column(name = "appellidos")
	private String appellidos;
	@Column(name = "edad")
	private int edad;
	@Column(name = "localidad")
	private String localidad;
	@Email
	@Column(name = "email")
	private String email;
	@Column(name = "passwd")
	private String passwd;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the appellidos
	 */
	public String getAppellidos() {
		return appellidos;
	}

	/**
	 * @param appellidos
	 *            the appellidos to set
	 */
	public void setAppellidos(String appellidos) {
		this.appellidos = appellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad
	 *            the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Contact(int id, String nombre, String appellidos, int edad, String localidad, String email, String passwd) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.appellidos = appellidos;
		this.edad = edad;
		this.localidad = localidad;
		this.email = email;
		this.passwd = passwd;
	}

>>>>>>> branch 'pruebas' of https://github.com/PruebasGITJava/PruebasMicro.git
	public Contact() {

	}
}
