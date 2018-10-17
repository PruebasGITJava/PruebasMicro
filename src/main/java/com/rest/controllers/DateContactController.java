package com.rest.controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.Contact;
import com.rest.mail.service.impl.MailServiceImpl;
import com.rest.services.GenerPasswdService;
import com.rest.services.impl.ContactServiceImpl;

import freemarker.template.TemplateException;

/**
 * The Class InicioSessionDateController.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app/contact")
public class DateContactController {

	/** The contact service impl. */
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;
	/** The mail service impl. */
	@Autowired
	@Qualifier("mailServiceImpl")
	private MailServiceImpl mailServiceImpl;

	/**
	 * Send mail.
	 *
	 * @param contact
	 *            the contact
	 * @return the response entity
	 */
	@PutMapping("/resetPasswd")
	public ResponseEntity<String> sendMail(@RequestBody Contact contact) {

		String passwd = GenerPasswdService.getPassword(
				GenerPasswdService.MINUSCULAS + GenerPasswdService.MAYUSCULAS + GenerPasswdService.ESPECIALES, 10);

		if (!contact.getEmail().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByAll();

			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())) {
					mailServiceImpl.sendSimpleMessagePasswdReset(contact, passwd);
					String encriptMD5 = DigestUtils.md5Hex(passwd);
					contactServiceImpl.updatePasswd(user, encriptMD5);

				}
			}
		}
		return ResponseEntity
				.ok(HttpStatus.OK + " Se envió un correo con el cambio de contraseña al mail: " + contact.getEmail());
	}

	/**
	 * Update email.
	 *
	 * @param contact
	 *            the contact
	 * @return the string
	 * @throws TemplateException
	 * @throws IOException
	 * @throws MessagingException
	 */
	@PutMapping("/emailEdit")
	public String updateEmail(@RequestBody Contact contact) throws MessagingException, IOException, TemplateException {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

			for (Contact user : contactos) {
				if (DigestUtils.md5Hex(contact.getPasswd()).equals(user.getPasswd())) {
					contactServiceImpl.updateDesactivation(user);
					contactServiceImpl.updateEmail(user, contact.getEmail());
					mailServiceImpl.sendSimpleMessageHTMLP(contact.getEmail(), user.getId());
					return ResponseEntity
							.ok(HttpStatus.OK + " Se ha modificado el email de manera manual con nombre actual: '"
									+ contact.getEmail()
									+ "', por su seguridad se ha mandado un correo de activación en su nuevo correo.")
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de cambio de email del usuario").toString();
	}

	/**
	 * Update passwd.
	 *
	 * @param contact
	 *            the contact
	 * @return the string
	 */
	@PutMapping("/passwdEdit")
	public String updatePasswd(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().length() > 8) {

					contactServiceImpl.updatePasswd(user, DigestUtils.md5Hex(contact.getPasswd()));

					return ResponseEntity.ok(HttpStatus.OK
							+ " Se ha modificado la contraseña de manera manual del email: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity
				.ok(HttpStatus.UNAUTHORIZED
						+ " Error de cambio contraseña del usuario o no cumple con los requisitos de seguridad.")
				.toString();
	}
}
