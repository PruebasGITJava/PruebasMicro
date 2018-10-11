package com.rest.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.Contact;

<<<<<<< HEAD
/**
 * The Interface LoginRepository.
 */
=======
>>>>>>> branch 'pruebas' of https://github.com/PruebasGITJava/PruebasMicro.git
@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<Contact, Serializable> {

}
