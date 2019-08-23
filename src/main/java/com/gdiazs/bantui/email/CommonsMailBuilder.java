package com.gdiazs.bantui.email;

import javax.mail.Session;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class CommonsMailBuilder {

  private HtmlEmail email;
 

  public CommonsMailBuilder() {
    email = new HtmlEmail();

  }


  public CommonsMailBuilder addFrom(String email) throws EmailException {
    this.email.setFrom(email);
    return this;
  }

  public CommonsMailBuilder addSubject(String email) {
    this.email.setSubject(email);
    return this;
  }

  public CommonsMailBuilder addTo(String... email) throws EmailException {
    this.email.addTo(email);
    return this;
  }

  public CommonsMailBuilder addHtmlMsg(String email) throws EmailException {
    this.email.setHtmlMsg(email);

    return this;
  }

  public HtmlEmail build() {
    return this.email;
  }

  public CommonsMailBuilder addMailSession(Session mailSession) {
    this.email.setMailSession(mailSession);
    return this;
  }



}
