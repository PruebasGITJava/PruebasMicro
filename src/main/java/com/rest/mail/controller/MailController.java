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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Contact;
import com.rest.mail.constants.RestMailConstants;
import com.rest.mail.service.impl.MailServiceImpl;
import com.rest.services.impl.ContactServiceImpl;

import freemarker.template.TemplateException;

@RestController
@RequestMapping(RestMailConstants.MAIL_REST_SUFIX)
public class MailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);
	@Autowired
	private MailServiceImpl mailServiceImpl;

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

	@PostMapping(RestMailConstants.SEND_MAIL)
	public void sendMail(@RequestParam(name = "type", required = true) String type,
			@RequestParam(name = "id", required = true) int id, @RequestParam(name = "to", required = true) String to)
			throws MessagingException, IOException, TemplateException {
		LOGGER.info("Rest method: sendMail()");
		// mailServiceImpl.sendSimpleMessage(to, "Hola", "caracola");
		mailServiceImpl.sendSimpleMessageHTMLP(to, id);

	}

	// CAMBIAR ESTADO DE ACTIVACIÓN
	@GetMapping(RestMailConstants.MAIL_REST_ACTIVATION)
	public String modifyActivation(@RequestParam(required = true, name = "id") int id) {
		Contact contact = contactServiceImpl.findById1(id);
		if (contact != null && contact.getActivation() == 0) {
			contactServiceImpl.updateActivation(contact);
			return ResponseEntity.ok(HttpStatus.OK + " Se ha activado su cuenta correctamente.").toString();
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de activacion del usuario").toString();
	}

}
