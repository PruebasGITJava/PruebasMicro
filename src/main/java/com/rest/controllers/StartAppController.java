package com.rest.controllers;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.Contact;
import com.rest.model.Mail;
import com.rest.services.EmailService;
import com.rest.services.impl.ContactServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app")
public class StartAppController {

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;
	@Autowired
	private EmailService emailService;

	// ALTA CONTACTO (SERVICIO REST)
	@PostMapping("/registro/add")
	public String addContact(@RequestBody Contact contact) throws Exception {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());
			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())) {
					return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de alta de un nuevo usuario").toString();

				}
			}

			Mail mail = new Mail();
			mail.setFrom(contact.getEmail());
			mail.setTo(contact.getEmail());
			mail.setSubject("Se ha dado de alta en la App, necesitamos que nos confirme su email.");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", "Usuario");
			model.put("location", "Pulse en el enlace para verificar email.");
			model.put("signature", "https://memorynotfound.com");
			mail.setModel(model);

			emailService.sendSimpleMessage(mail);

=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.Contact;
import com.rest.services.impl.ContactServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/app")
public class StartAppController {

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

	// ALTA CONTACTO (SERVICIO REST)
	@PostMapping("/registro/add")
	public String addContact(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());
			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())) {
					return ResponseEntity
							.ok(HttpStatus.UNAUTHORIZED + " Error de alta de un nuevo usuario, ya registrado")
							.toString();
				}
			}
>>>>>>> branch 'pruebas' of https://github.com/PruebasGITJava/PruebasMicro.git
			contactServiceImpl.addContact(contact);
			return ResponseEntity.ok(HttpStatus.OK + " Se dio de alta el usuario con email: " + contact.getEmail())
					.toString();
		}

		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de alta de un nuevo usuario").toString();
	}

	// BAJA CONTACTO (SERVICIO REST)
	@DeleteMapping("/registro/del")
	public String deleteContact(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());
			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail())) {
					contactServiceImpl.removeContact(user);
					return ResponseEntity
							.ok(HttpStatus.OK + " Se dio de baja el usuario con email: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de baja de un nuevo usuario").toString();
	}

	// CONECTAR A LA APLICACION (SERVICIO REST)
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().isEmpty()) {
			for (Contact user : contactServiceImpl.findByNombreOrderById(contact.getNombre())) {
				if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().equals(user.getPasswd())) {
					return ResponseEntity.ok(HttpStatus.OK + " Bienvenido");
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Datos de autenticación incorrectos");
	}

	// DESCONECTAR LA APLICACION (SERVICIO REST)
	@PostMapping("/logout")
	public ResponseEntity<?> outlogin(@RequestBody Contact contact) {
		for (Contact user : contactServiceImpl.findByNombreOrderById(contact.getNombre())) {
			if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().equals(user.getPasswd())) { // entonces
																												// aqui
																												// como
																												// lo
																												// hago?
				return ResponseEntity.ok(HttpStatus.OK + " Saliste satisfactoriamente");
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " No has iniciado sesión");
	}
}