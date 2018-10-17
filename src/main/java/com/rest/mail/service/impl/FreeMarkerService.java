package com.rest.mail.service.impl;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rest.mail.TypeMail;

import freemarker.template.Configuration;

/**
 * 
 * @author icozar
 *
 */
@Component("freeMarkerService")
public class FreeMarkerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerService.class);

	@Autowired
	private Configuration freemarkerConfiguration;

	/**
	 * Process the html template
	 *
	 * @param model
	 * @return
	 */
	public String geFreeMarkerTemplate(Map<String, Object> model, TypeMail type) {
		LOGGER.info("Method: geFreeMarkerTemplate");
		if (model.isEmpty()) {
			return geFreeMarkerTemplateSimple(type);
		}
<<<<<<< HEAD
		return geFreeMarkerTemplateContent(model, type);
=======
		return geFreeMarkerTemplateContent(type);
>>>>>>> refs/remotes/origin/pruebas

	}

	/**
	 * Gets a simple template processed
	 * 
	 * @param type
	 * @return
	 */
<<<<<<< HEAD
	public String geFreeMarkerTemplateSimple(TypeMail type) {
		LOGGER.info("Method: geFreeMarkerTemplateContent");
=======
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
		StringBuilder content = new StringBuilder();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(type.getTemplate()), null));
=======
	public String geFreeMarkerTemplateSimple(TypeMail type) {
		LOGGER.info("Method: geFreeMarkerTemplateContent");
		StringBuilder content = new StringBuilder();
>>>>>>> refs/remotes/origin/pruebas

<<<<<<< HEAD
			LOGGER.info("Content: {}", content);
=======
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(type.getTemplate()), null));
			String conten = content.toString();
			LOGGER.info(conten);
>>>>>>> refs/remotes/origin/pruebas

			return content.toString();
		} catch (Exception e) {
<<<<<<< HEAD
			LOGGER.info("Exception occured while processing fmtemplate: {}", e.getMessage());
=======
			MessageFormat.format("Exception occured while processing fmtemplate: {0}.", e.getMessage());
>>>>>>> refs/remotes/origin/pruebas
		}
		return null;
	}

	/**
	 * Gets a template with content processed
	 * 
	 * @param model
	 * @param type
	 * @return
	 */
<<<<<<< HEAD
	public String geFreeMarkerTemplateContent(Map<String, Object> model, TypeMail type) {
=======
	public String geFreeMarkerTemplateContent(TypeMail type) {
>>>>>>> refs/remotes/origin/pruebas
		LOGGER.info("Method: geFreeMarkerTemplateContent");
		StringBuilder content = new StringBuilder();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(type.getTemplate()), null));

<<<<<<< HEAD
			LOGGER.info("Content: {}", content);

			return content.toString();
		} catch (Exception e) {
			LOGGER.info("Exception occured while processing fmtemplate: {}", e.getMessage());
=======
			String conten = content.toString();
			LOGGER.info(conten);

			return content.toString();
		} catch (Exception e) {
			MessageFormat.format("Exception occured while processing fmtemplate {0}.", e.getMessage());

>>>>>>> refs/remotes/origin/pruebas
		}
		return null;
	}
}
