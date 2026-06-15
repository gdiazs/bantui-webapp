package com.gdiazs.bantui.web.security;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import com.gdiazs.bantui.users.Authority;
import com.gdiazs.bantui.users.User;
import com.gdiazs.bantui.users.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class BantuiIdentityStore implements IdentityStore {

  @Inject
  private UserRepository userRepository;

  @Inject
  private PasswordHasher passwordHasher;

  public BantuiIdentityStore() {
  }

  BantuiIdentityStore(UserRepository userRepository, PasswordHasher passwordHasher) {
    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
  }

  @Override
  public CredentialValidationResult validate(Credential credential) {
    if (!(credential instanceof UsernamePasswordCredential usernamePassword)) {
      return INVALID_RESULT;
    }

    User user = userRepository.findByUsernameOrEmail(usernamePassword.getCaller()).orElse(null);
    if (user == null || !isActive(user)
        || !passwordHasher.verify(usernamePassword.getPassword().getValue(), user.getPassword())) {
      return INVALID_RESULT;
    }

    Set<String> groups = user.getAuthorities() == null
        ? Collections.emptySet()
        : user.getAuthorities().stream()
            .map(Authority::getAuthority)
            .collect(Collectors.toUnmodifiableSet());
    return new CredentialValidationResult(user.getUsername(), groups);
  }

  private boolean isActive(User user) {
    return user.isAccountNonExpired()
        && user.isAccountNonLocked()
        && user.isCredentialsNonExpired();
  }
}
