package com.rest.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.Contact;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<Contact, Serializable> {

}
