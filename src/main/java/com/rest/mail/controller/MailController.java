package com.rest.mail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.mail.constants.RestMailConstants;
import com.rest.mail.service.impl.MailServiceImpl;

@RestController
@RequestMapping(RestMailConstants.MAIL_REST_SUFIX)
public class MailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);
    @Autowired
    private MailServiceImpl mailServiceImpl;

    @PostMapping(RestMailConstants.SEND_MAIL)
    public void sendMail(@RequestParam(name = "type", required = true) String type, @RequestParam(name = "id", required = true) String id, @RequestParam(name = "to", required = true) String to) {
        LOGGER.info("Rest method: sendMail()");
        mailServiceImpl.sendSimpleMessage(to, "Hola", "caracola");
    }

}
