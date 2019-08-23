package com.gdiazs.bantui.register;

public enum RegisterStatus {

  EMAIL_TAKEN(0),
  USERNAME_EXISTS(1),
  USER_CREATED(2),
  USER_NOT_CREATED(3);
  
  
  private final int value;

  private RegisterStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  
  

  
  

  
  

}
