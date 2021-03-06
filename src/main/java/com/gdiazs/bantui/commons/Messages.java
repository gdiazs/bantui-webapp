package com.gdiazs.bantui.commons;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageContextConfig;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
@MessageContextConfig(messageSource = {"i18n.messages"})
public interface Messages {
  @MessageTemplate("{security.access.denied}")
  String getSecurityAccessDenied();

  @MessageTemplate("{register.error.userFound}")
  String getUserFound();

  @MessageTemplate("{register.error.emailFound}")
  String getEmailFound();
  
  
  @MessageTemplate("{register.info.accountCreated}")
  String getAccountCreated();


}
