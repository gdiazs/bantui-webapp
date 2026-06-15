package com.gdiazs.bantui.web.security;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.INVALID;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gdiazs.bantui.users.Authority;
import com.gdiazs.bantui.users.User;
import com.gdiazs.bantui.users.UserRepository;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import java.util.List;
import org.junit.jupiter.api.Test;

class BantuiIdentityStoreTest {

  private final PasswordHasher passwordHasher = new PasswordHasher();

  @Test
  void validatesAnActiveUserAndReturnsGroups() {
    User user = activeUser("memo", "secret", "ROLE_CONSUMER");
    BantuiIdentityStore identityStore = new BantuiIdentityStore(repositoryReturning(user), passwordHasher);

    var result = identityStore.validate(new UsernamePasswordCredential("memo", "secret"));

    assertEquals(VALID, result.getStatus());
    assertEquals("memo", result.getCallerPrincipal().getName());
    assertTrue(result.getCallerGroups().contains("ROLE_CONSUMER"));
  }

  @Test
  void rejectsInvalidPasswordsAndDisabledUsers() {
    User user = activeUser("memo", "secret", "ROLE_CONSUMER");
    BantuiIdentityStore identityStore = new BantuiIdentityStore(repositoryReturning(user), passwordHasher);

    assertEquals(INVALID,
        identityStore.validate(new UsernamePasswordCredential("memo", "wrong")).getStatus());

    user.setEnabled(User.NO);
    assertEquals(INVALID,
        identityStore.validate(new UsernamePasswordCredential("memo", "secret")).getStatus());
  }

  private User activeUser(String username, String password, String role) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordHasher.hash(password));
    user.setEnabled(User.YES);
    user.setAccountNonExpired(User.YES);
    user.setAccountNonLocked(User.YES);
    user.setCredentialsNonExpired(User.YES);

    Authority authority = new Authority();
    authority.setAuthority(role);
    user.setAuthorities(List.of(authority));
    return user;
  }

  private UserRepository repositoryReturning(User user) {
    return new UserRepository() {
      @Override
      public User findByUsername(String username) {
        return user.getUsername().equals(username) ? user : null;
      }
    };
  }
}
