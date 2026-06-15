package com.gdiazs.bantui.commons;

import java.util.Locale;
import java.util.ResourceBundle;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;

@ApplicationScoped
public class Messages {

  public String getSecurityAccessDenied() {
    return get("security.access.denied");
  }

  public String getUserFound() {
    return get("register.error.userFound");
  }

  public String getEmailFound() {
    return get("register.error.emailFound");
  }

  public String getAccountCreated() {
    return get("register.info.accountCreated");
  }

  private String get(String key) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Locale locale = facesContext == null ? Locale.getDefault() : facesContext.getViewRoot().getLocale();
    return ResourceBundle.getBundle("i18n.messages", locale).getString(key);
  }
}
