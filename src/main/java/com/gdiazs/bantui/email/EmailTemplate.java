package com.gdiazs.bantui.email;

public enum EmailTemplate {

  CONFIRMATION_TEMPLATE("emails/account-confirmation");

  private String value;



  private EmailTemplate(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }



}
