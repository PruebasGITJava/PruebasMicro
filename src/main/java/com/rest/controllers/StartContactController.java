package com.rest.controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.constants.ConstantApp;
import com.rest.entity.Contact;
import com.rest.mail.controller.MailController;
import com.rest.mail.service.impl.MailServiceImpl;
import com.rest.services.impl.ContactServiceImpl;

import freemarker.template.TemplateException;

/**
 * The Class StartAppController.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app")
public class StartContactController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

	/** The contact service impl. */
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

	/** The mail service impl. */
	@Autowired
	@Qualifier("mailServiceImpl")
	private MailServiceImpl mailServiceImpl;

	/**
	 * Adds the contact.
	 *
	 * @param contact
	 *            the contact
	 * @return the string
	 * @throws MessagingException
	 *             the messaging exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TemplateException
	 *             the template exception
	 */
	@PostMapping("/registry/add")
	public String addContact(@RequestBody Contact contact) throws MessagingException, IOException, TemplateException {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByAll();
			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())) {
					LOGGER.info(ConstantApp.CONTACTS, contact.getEmail(), ConstantApp.USER, user.getEmail(), "'.");
					return ResponseEntity
							.ok(HttpStatus.UNAUTHORIZED + " Error de alta de un nuevo usuario, ya registrado")
							.toString();
				}
			}
			contact.setActivation(0);
			LOGGER.info("Rest method: setActivation(0)");
			String encriptMD5 = DigestUtils.md5Hex(contact.getPasswd());
			contact.setPasswd(encriptMD5);
			LOGGER.info("Rest method: md5Hex(paswd)");
			contactServiceImpl.addContact(contact);
			LOGGER.info("Rest method: addContact()");
			mailServiceImpl.sendSimpleMessageHTMLP(contact.getEmail(), contact.getId());
			return ResponseEntity.ok(HttpStatus.OK + " Se dio de alta el usuario con email: '" + contact.getEmail()
					+ "', en breve recibirá un correo para la confirmación de la cuenta.").toString();
		}

		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de alta de un nuevo usuario").toString();
	}

	/**
	 * Delete contact.
	 *
	 * @param contact
	 *            the contact
	 * @return the string
	 */
	@DeleteMapping("/registry/del")
	public String deleteContact(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());
			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())
						&& DigestUtils.md5Hex(contact.getPasswd()).equals(user.getPasswd())) {
					LOGGER.info(ConstantApp.CONTACTS, contact.getEmail(), ConstantApp.USER, user.getEmail(), "'.");
					contactServiceImpl.removeContact(user);
					LOGGER.info("Rest method: removeContact()");
					return ResponseEntity
							.ok(HttpStatus.OK + " Se dio de baja el usuario con email: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de baja de un nuevo usuario").toString();
	}

	/**
	 * Login.
	 *
	 * @param contact
	 *            the contact
	 * @return the response entity
	 */
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().isEmpty()) {

			for (Contact user : contactServiceImpl.findByAll()) {
				if (contact.getEmail().equals(user.getEmail())
						&& DigestUtils.md5Hex(contact.getPasswd()).equals(user.getPasswd())
						&& user.getActivation() == 1) {
					LOGGER.info("Rest params: --Contact(new): '" + contact.getEmail() + ", " + contact.getPasswd()
							+ "' --User(bbdd): '" + user.getEmail() + ", " + user.getPasswd() + "'.");
					return ResponseEntity.ok(HttpStatus.OK + " Bienvenido");
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Datos de autenticación incorrectos o no activados.");
	}

	/**
	 * Outlogin.
	 *
	 * @param contact
	 *            the contact
	 * @return the response entity
	 */
	@PostMapping("/logout")
	public ResponseEntity<String> outlogin(@RequestBody Contact contact) {
		for (Contact user : contactServiceImpl.findByNombreOrderById(contact.getNombre())) {
			if (contact.getEmail().equals(user.getEmail())
					&& DigestUtils.md5Hex(contact.getPasswd()).equals(user.getPasswd())) {
				LOGGER.info("Rest params: --Contact(new): '" + contact.getEmail() + ", " + contact.getPasswd()
						+ "' --User(bbdd): '" + user.getEmail() + ", " + user.getPasswd() + "'.");
				return ResponseEntity.ok(HttpStatus.OK + " Saliste satisfactoriamente");
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " No has iniciado sesión");
	}
}