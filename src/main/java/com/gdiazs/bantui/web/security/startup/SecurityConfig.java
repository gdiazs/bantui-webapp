package com.gdiazs.bantui.web.security.startup;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Dependent
public class SecurityConfig {

  @Named
  @Singleton
  @Produces
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }
}
