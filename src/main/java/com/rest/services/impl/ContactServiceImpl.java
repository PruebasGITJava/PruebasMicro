package com.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.entity.Contact;
import com.rest.repositories.LoginRepository;
import com.rest.services.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("loginRepository")
	private LoginRepository loginRepository;

	@Override
	public String updateActivation(Contact contact) {

		contact.setActivation(1);
		loginRepository.save(contact);
		return " ";
	}

	@Override
<<<<<<< HEAD
	public String updateDesactivation(Contact contact) {

		contact.setActivation(0);
		loginRepository.save(contact);
		return " ";
	}

	@Override
=======
>>>>>>> refs/remotes/origin/pruebas
	public List<Contact> findByAll() {
		return loginRepository.findAll();
	}

	@Override
	public List<Contact> findByNombreOrderById(String nombre) {
		return loginRepository.findAll();
	}

	@Override
	public Contact addContact(Contact contact) {
		return loginRepository.save(contact);

	}

	@Override
	public void removeContact(Contact contact) {
		loginRepository.delete(contact);
	}

	@Override
	public String updateContact(Contact contact, String nombre, String appellidos, int edad, String localidad) {

		contact.setNombre(nombre);
		contact.setAppellidos(appellidos);
		contact.setEdad(edad);
		contact.setLocalidad(localidad);

		loginRepository.save(contact);
		return " ";
	}

	@Override
	public Optional<Contact> findById(int id) {
		return loginRepository.findById(id);
	}

	@Override
	public Contact findById1(int id) {
<<<<<<< HEAD
		return loginRepository.findById(id).get();
=======
		return ((ContactService) loginRepository).findById1(id);
>>>>>>> refs/remotes/origin/pruebas

	}

	@Override
	public String updatePasswd(Contact contact, String passwd) {
		contact.setPasswd(passwd);
		loginRepository.save(contact);
		return null;
	}

	@Override
	public String updateEmail(Contact contact, String email) {
		contact.setEmail(email);
		loginRepository.save(contact);
		return null;
	}

}
