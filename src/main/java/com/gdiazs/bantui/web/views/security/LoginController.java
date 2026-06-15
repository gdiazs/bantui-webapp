package com.gdiazs.bantui.web.views.security;

import static jakarta.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static jakarta.security.enterprise.AuthenticationStatus.SUCCESS;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
public class LoginController {

  @Inject
  private SecurityContext securityContext;

  private String username;
  private String password;

  public String login() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

    AuthenticationStatus status = securityContext.authenticate(request, response,
        withParams().newAuthentication(true)
            .credential(new UsernamePasswordCredential(username, password)));

    if (status == SUCCESS) {
      return "/pages/dashboard.xhtml?faces-redirect=true";
    }
    if (status == SEND_CONTINUE) {
      FacesContext.getCurrentInstance().responseComplete();
      return null;
    }

    FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales inválidas", null));
    return null;
  }

  public void logout() throws ServletException, IOException {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
    request.logout();
    externalContext.invalidateSession();
    externalContext.redirect(externalContext.getRequestContextPath() + "/pages/signin.xhtml");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
