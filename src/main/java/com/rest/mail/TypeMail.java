package com.rest.mail;

/**
 * Enum of templates
 *
 * @author ciber
 *
 */
public enum TypeMail {

    HELLO("email.html"), HELLO_PDF("hello.pdf");

    private String template;

    private TypeMail(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return this.template;
    }
}
