package com.rest.mail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> refs/remotes/origin/pruebas
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Contact;
import com.rest.mail.constants.RestMailConstants;
<<<<<<< HEAD
import com.rest.services.impl.ContactServiceImpl;
=======
import com.rest.mail.service.impl.MailServiceImpl;
import com.rest.services.impl.ContactServiceImpl;

import freemarker.template.TemplateException;
>>>>>>> refs/remotes/origin/pruebas

@RestController
@RequestMapping(RestMailConstants.MAIL_REST_SUFIX)
public class MailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);
<<<<<<< HEAD
=======
	@Autowired
	private MailServiceImpl mailServiceImpl;
>>>>>>> refs/remotes/origin/pruebas

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

<<<<<<< HEAD
	// CAMBIAR ESTADO DE ACTIVACIÓN Y REDIRIGIR A UNA PAGINA DE INFORMACIÓN
	@GetMapping(RestMailConstants.MAIL_REST_ACTIVATION)
	public String modifyActivation(@RequestParam(required = true, name = "id") int id) {
		Contact contact = contactServiceImpl.findById1(id);
		if (contact != null && contact.getActivation() == 0) {
			contactServiceImpl.updateActivation(contact);
			LOGGER.info("Rest method: updateActivation(0)");
=======
	@PostMapping(RestMailConstants.SEND_MAIL)
	public void sendMail(@RequestParam(name = "type", required = true) String type,
			@RequestParam(name = "id", required = true) int id, @RequestParam(name = "to", required = true) String to)
			throws MessagingException, IOException, TemplateException {
		LOGGER.info("Rest method: sendMail()");
		mailServiceImpl.sendSimpleMessage(to, "Hola", "caracola");
		mailServiceImpl.sendSimpleMessageHTMLP(to, id);

	}

	// CAMBIAR ESTADO DE ACTIVACIÓN
	@GetMapping(RestMailConstants.MAIL_REST_ACTIVATION)
	public String modifyActivation(@RequestParam(required = true, name = "id") int id) {
		Contact contact = contactServiceImpl.findById1(id);
		if (contact != null && contact.getActivation() == 0) {
			contactServiceImpl.updateActivation(contact);
>>>>>>> refs/remotes/origin/pruebas
			return ResponseEntity.ok(HttpStatus.OK + " Se ha activado su cuenta correctamente.").toString();
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de activacion del usuario").toString();
	}

}
