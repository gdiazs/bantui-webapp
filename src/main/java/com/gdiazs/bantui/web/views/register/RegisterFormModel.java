package com.gdiazs.bantui.web.views.register;

import javax.enterprise.inject.Model;

@Model
public class RegisterFormModel {


  private String username;

  private String email;

  private String password;

  private String rePassword;

  private Boolean acceptTermsAndUseConditions;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRePassword() {
    return rePassword;
  }

  public void setRePassword(String rePassword) {
    this.rePassword = rePassword;
  }

  public Boolean getAcceptTermsAndUseConditions() {
    return acceptTermsAndUseConditions;
  }

  public void setAcceptTermsAndUseConditions(Boolean acceptTermsAndUseConditions) {
    this.acceptTermsAndUseConditions = acceptTermsAndUseConditions;
  }

  @Override
  public String toString() {
    return "RegisterFormModel [username=" + username + ", email=" + email + ", password=" + password
        + ", rePassword=" + rePassword + ", acceptTermsAndUseConditions="
        + acceptTermsAndUseConditions + "]";
  }



}
