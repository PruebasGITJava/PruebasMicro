package com.rest.mail.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.logging.Logger;
>>>>>>> refs/remotes/origin/pruebas

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rest.entity.Contact;
import com.rest.mail.service.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("mailServiceImpl")
public class MailServiceImpl implements MailService {

	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	public FreeMarkerService freeMarkerService;
	@Autowired
	private Configuration freemarkerConfiguration;
<<<<<<< HEAD
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
=======
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
	@Override
	public void sendSimpleMessagePasswdReset(Contact contact, String passwd) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(contact.getEmail());
			message.setSubject("Email para restablecer su contraseña.");
			message.setText("Tu nueva contraseña será: " + passwd);
=======
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MailServiceImpl.class);
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
			emailSender.send(message);
		} catch (MailException exception) {
			LOGGER.error("Context", exception);
		}
=======
	@Override
	public void sendSimpleMessageHTMLP(String to, int id) throws MessagingException, IOException, TemplateException {
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
	}
=======
		com.rest.model.Mail mail = new com.rest.model.Mail();
		mail.setTo(to);
		mail.setSubject("Email de confirmación App");
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
	@Override
	public void sendSimpleMessageHTMLP(String to, int id) throws MessagingException, IOException, TemplateException {
=======
		Map<String, Object> model = new HashMap<>();
		model.put("name", mail.getFrom());
		model.put("location", "Madrid");
		model.put("signature", "http://localhost:8080/message/activation?id=" + id);
		mail.setModel(model);
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		com.rest.mail.model.Mail mail = new com.rest.mail.model.Mail();
		mail.setTo(to);
		mail.setSubject("Email de confirmación App");
=======
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		Map<String, Object> model = new HashMap<>();
		model.put("name", mail.getFrom());
		model.put("location", "Madrid");
		model.put("signature", "http://localhost:8080/message/activation?id=" + id);
		mail.setModel(model);
=======
		helper.addAttachment("logo.png", new ClassPathResource("/images/memorynotfound-logo.png"));
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
=======
		Template t = freemarkerConfiguration.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		helper.addAttachment("logo.png", new ClassPathResource("/images/memorynotfound-logo.png"));
=======
		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		Template t = freemarkerConfiguration.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
=======
		emailSender.send(message);
	}
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
=======
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		emailSender.send(message);
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			LOGGER.error("context", exception);
		}

	}

	@Override
	public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
			String... templateArgs) {
		String text = String.format(template.getText(), templateArgs);
		sendSimpleMessage(to, subject, text);

	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			// pass 'true' to the constructor to create a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);

			emailSender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("context", e);
=======
			emailSender.send(message);
		} catch (MailException exception) {
			LOGGER.info("Exception: " + exception);

		}

	}

	@Override
	public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
			String... templateArgs) {
		String text = String.format(template.getText(), templateArgs);
		sendSimpleMessage(to, subject, text);

	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			// pass 'true' to the constructor to create a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);

			emailSender.send(message);
		} catch (MessagingException e) {
			LOGGER.info("Exception: " + e);
>>>>>>> refs/remotes/origin/pruebas
		}

	}

}
