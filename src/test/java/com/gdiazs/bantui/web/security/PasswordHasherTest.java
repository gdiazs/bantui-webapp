package com.gdiazs.bantui.web.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordHasherTest {

  private final PasswordHasher passwordHasher = new PasswordHasher();

  @Test
  void verifiesNewAndSpringCompatibleBcryptHashes() {
    String encoded = passwordHasher.hash("correct horse battery staple");

    assertTrue(passwordHasher.verify("correct horse battery staple".toCharArray(), encoded));
    assertTrue(passwordHasher.verify("password".toCharArray(),
        "$2a$11$1ltbTWeIJKEbsiHDX8vE9u2tNrnolg/DkFyWzurhc0zO8GRQU9CiG"));
    assertFalse(passwordHasher.verify("wrong".toCharArray(), encoded));
  }
}
