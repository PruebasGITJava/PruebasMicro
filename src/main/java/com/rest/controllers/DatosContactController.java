package com.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.entity.Contact;
import com.rest.services.impl.ContactServiceImpl;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class DatosContactController {

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactServiceImpl contactServiceImpl;

	// INSERTAR/MODIFICAR/ELIMINAR DATOS DE CONTACTO (SERVICIO REST)
	@PutMapping("/person/datos")
	public String updateContact1(@RequestBody Contact contact) {
		if (!contact.getEmail().trim().isEmpty() && !contact.getPasswd().trim().isEmpty()) {
			List<Contact> contactos = contactServiceImpl.findByNombreOrderById(contact.getNombre());

			for (Contact user : contactos) {
				if (contact.getEmail().equals(user.getEmail()) && contact.getPasswd().equals(user.getPasswd())) {

					contactServiceImpl.updateContact(user, contact.getNombre(), contact.getAppellidos(),
							contact.getEdad(), contact.getLocalidad());

					return ResponseEntity.ok(
							HttpStatus.OK + " Se ha modificado los datos del usuario con email: " + contact.getEmail())
							.toString();
				}
			}
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de modificación de datos del usuario").toString();
	}

	// VISUALIZAR DATOS DE CONTACTO (SERVICIO REST)
	@GetMapping("/person/view")
	public String viewContact(@RequestParam(required = true, name = "id") int id) {
		Optional<Contact> contact = contactServiceImpl.findById(id);
		if (contact != null) {
			return ResponseEntity.ok(HttpStatus.OK + " Se ha visualizado los datos del usuario con email: "
					+ contact.filter(p -> p.getEmail() != null).map(x -> x.getEmail()).toString() + "\n" + "ID: "
					+ contact.get().getId() + ", NOMBRE: " + contact.get().getNombre() + ", APELLIDOS: "
					+ contact.get().getAppellidos() + ", EDAD: " + contact.get().getEdad() + ", LOCALIDAD: "
					+ contact.get().getLocalidad()).toString();
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED + " Error de visualización de datos del usuario").toString();
	}
}
