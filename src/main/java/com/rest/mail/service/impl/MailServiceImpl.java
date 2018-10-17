package com.rest.mail.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	public void sendSimpleMessagePasswdReset(Contact contact, String passwd) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(contact.getEmail());
			message.setSubject("Email para restablecer su contraseña.");
			message.setText("Tu nueva contraseña será: " + passwd);

			emailSender.send(message);
		} catch (MailException exception) {
			LOGGER.error("Context", exception);
		}

	}

	@Override
	public void sendSimpleMessageHTMLP(String to, int id) throws MessagingException, IOException, TemplateException {

		com.rest.mail.model.Mail mail = new com.rest.mail.model.Mail();
		mail.setTo(to);
		mail.setSubject("Email de confirmación App");

		Map<String, Object> model = new HashMap<>();
		model.put("name", mail.getFrom());
		model.put("location", "Madrid");
		model.put("signature", "http://localhost:8080/message/activation?id=" + id);
		mail.setModel(model);

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		helper.addAttachment("logo.png", new ClassPathResource("/images/memorynotfound-logo.png"));

		Template t = freemarkerConfiguration.getTemplate("email-template.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

		helper.setTo(mail.getTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());

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
		}

	}

}
