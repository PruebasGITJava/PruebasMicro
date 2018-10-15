package com.rest.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;

import freemarker.template.TemplateException;

public interface MailService {

	void sendSimpleMessage(String to, String subject, String text);

	void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs);

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

	void sendSimpleMessageHTMLP(String to, int id) throws MessagingException, IOException, TemplateException;
}