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
import java.util.Optional;
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
  void validatesARecentlyRegisteredUserByEmail() {
    User user = new User.UserBuilder()
        .addUsername("memo")
        .addEmail("memo@example.com")
        .addPassword(passwordHasher.hash("secret"))
        .addRoles("ROLE_CONSUMER")
        .build();
    BantuiIdentityStore identityStore =
        new BantuiIdentityStore(repositoryReturning(user), passwordHasher);

    var result = identityStore.validate(
        new UsernamePasswordCredential("memo@example.com", "secret"));

    assertEquals(VALID, result.getStatus());
    assertEquals("memo", result.getCallerPrincipal().getName());
  }

  @Test
  void rejectsInvalidPasswordsAndLockedUsers() {
    User user = activeUser("memo", "secret", "ROLE_CONSUMER");
    BantuiIdentityStore identityStore = new BantuiIdentityStore(repositoryReturning(user), passwordHasher);

    assertEquals(INVALID,
        identityStore.validate(new UsernamePasswordCredential("memo", "wrong")).getStatus());

    user.setAccountNonLocked(User.NO);
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
      public Optional<User> findByUsernameOrEmail(String login) {
        return user.getUsername().equals(login) || user.getEmail().equals(login)
            ? Optional.of(user)
            : Optional.empty();
      }
    };
  }
}
