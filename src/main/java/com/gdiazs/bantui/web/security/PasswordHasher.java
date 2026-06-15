package com.gdiazs.bantui.web.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordHasher {

  private static final int COST = 11;

  public String hash(String value) {
    return BCrypt.withDefaults().hashToString(COST, value.toCharArray());
  }

  public boolean verify(char[] value, String encodedValue) {
    return encodedValue != null && BCrypt.verifyer().verify(value, encodedValue).verified;
  }
}
