package com.gdiazs.bantui.web.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage = "/pages/signin.xhtml",
        errorPage = "/pages/signin.xhtml?error=true",
        useForwardToLogin = false))
public class JakartaSecurityConfig {
}
