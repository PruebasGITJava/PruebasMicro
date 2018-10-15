package com.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.entity.Contact;

@Component("contactService")
public interface ContactService {
	public abstract List<Contact> findByNombreOrderById(String nombre);

	public abstract Optional<Contact> findById(int id);

	public abstract Contact addContact(Contact contact);

	public abstract void removeContact(Contact contact);

	public abstract String updateContact(Contact contact, String nombre, String appellidos, int edad, String localidad);

	public abstract String updatePasswd(Contact contact, String passwd);

	public abstract String updateEmail(Contact contact, String email);

	public abstract String updateActivation(Contact contact);

	public abstract Contact findById1(int id);

	public abstract List<Contact> findByAll();

}
