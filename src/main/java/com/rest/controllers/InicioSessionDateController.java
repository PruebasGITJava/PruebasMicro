package com.rest.controllers;

import java.text.MessageFormat;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.config.ApplicationConfig;
import com.rest.entity.Contact;
import com.rest.services.GenerPasswdService;
import com.rest.services.impl.ContactServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/apc")
public class InicioSessionDateController {

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

	// RESTABLECER CONTRASEÑA Y ENVIAR POR EMAIL
	@PutMapping("/person/resetPasswd")
	public ResponseEntity<String> endMail(@RequestBody Contact contact, String to, String subject, String mensage) {

		Session session = Session.getInstance(ApplicationConfig.getMailSender(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(contact.getEmail(), contact.getPasswd());
			}
		});
		try {
			String passwd = GenerPasswdService.getPassword(
					GenerPasswdService.MINUSCULAS + GenerPasswdService.MAYUSCULAS + GenerPasswdService.ESPECIALES, 10);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(contact.getEmail()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact.getEmail()));// email->receptivo
			message.setSubject("Correo de generación de una nueva contraseña");
			message.setText("Tu nueva contraseña será: " + passwd);
			Transport.send(message);
			if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
				List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

				for (Contact user : contactos) {
					if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().length() > 8) {

						contactServiceImpl.updatePasswd(user, passwd);

					}
				}
			}
			return ResponseEntity.ok(
					HttpStatus.OK + " Se envió un correo con el cambio de contraseña al mail: " + contact.getEmail());

		} catch (MessagingException e) {
			MessageFormat.format("Result {0}.", e);
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de cambio de contraseña.");

	}

	// CAMBIAR EMAIL
	@PutMapping("/person/emailEdit")
	public String updateEmail(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

			for (Contact user : contactos) {
				if (contact.getPasswd().equals(user.getPasswd())) {

					contactServiceImpl.updateEmail(user, contact.getEmail());

					return ResponseEntity.ok(HttpStatus.OK
							+ " Se ha modificado el email de manera manual con nombre actual: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de cambio de email del usuario").toString();
	}

	// CAMBIAR CONTRASEÑA
	@PutMapping("/person/passwdEdit")
	public String updatePasswd(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().length() > 8) {

					contactServiceImpl.updatePasswd(user, contact.getPasswd());

					return ResponseEntity.ok(HttpStatus.OK
							+ " Se ha modificado la contraseña de manera manual del email: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de cambio contraseña del usuario").toString();
	}
}
