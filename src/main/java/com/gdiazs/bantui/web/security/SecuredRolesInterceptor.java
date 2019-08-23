package com.gdiazs.bantui.web.security;

import java.util.Collection;
import java.util.Optional;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured
@Interceptor
@Priority(value = Interceptor.Priority.APPLICATION)
public class SecuredRolesInterceptor {

  @AroundInvoke
  public Object validateSpringSecurityRoles(InvocationContext invocationContext) throws Exception {

    final Secured annotation = invocationContext.getMethod().getAnnotation(Secured.class);
    final String[] roles = annotation.value();
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Object proceed = null;

    Boolean hasAleastOneRole = false;

    for (String rol : roles) {

      final Optional<? extends GrantedAuthority> findAny =
          authorities.stream().filter(auth -> auth.getAuthority().equals(rol)).findAny();

      if (findAny.isPresent()) {
        hasAleastOneRole = findAny.isPresent();
        break;

      }

    }

    if (hasAleastOneRole) {
      proceed = invocationContext.proceed();

    } else {
      StringBuilder message = new StringBuilder();

      message.append("User: ").append(authentication.getName())
          .append(" does not have access to resource: ").append(invocationContext.getMethod());

      throw new AccessDeniedException(message.toString());

    }

    return proceed;
  }
}
