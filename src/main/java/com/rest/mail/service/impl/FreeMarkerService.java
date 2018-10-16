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
		return geFreeMarkerTemplateContent(type);

	}

	/**
	 * Gets a simple template processed
	 * 
	 * @param type
	 * @return
	 */

	public String geFreeMarkerTemplateSimple(TypeMail type) {
		LOGGER.info("Method: geFreeMarkerTemplateContent");
		StringBuilder content = new StringBuilder();

		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(type.getTemplate()), null));
			String conten = content.toString();
			LOGGER.info(conten);

			return content.toString();
		} catch (Exception e) {
			MessageFormat.format("Exception occured while processing fmtemplate: {0}.", e.getMessage());
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
	public String geFreeMarkerTemplateContent(TypeMail type) {
		LOGGER.info("Method: geFreeMarkerTemplateContent");
		StringBuilder content = new StringBuilder();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate(type.getTemplate()), null));

			String conten = content.toString();
			LOGGER.info(conten);

			return content.toString();
		} catch (Exception e) {
			MessageFormat.format("Exception occured while processing fmtemplate {0}.", e.getMessage());

		}
		return null;
	}
}
