package com.gdiazs.bantui.email;

import java.util.Locale;
import java.util.Map;

public interface EmailSender {

  void loadTemplate(String templatePath);

  void addVariables(Map<String, Object> variables);

  void send(Locale locale, String subject, String from, String... to) throws EmailSenderException;

  void sendAsync(Locale locale, String subject, String from, String... to)
      throws EmailSenderException;



}
