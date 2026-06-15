package com.gdiazs.bantui.register;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import com.gdiazs.bantui.web.security.PasswordHasher;
import com.gdiazs.bantui.users.ConfirmationToken;
import com.gdiazs.bantui.users.ConfirmationTokenPK;
import com.gdiazs.bantui.users.ConfirmationTokenRepository;
import com.gdiazs.bantui.users.User;
import com.gdiazs.bantui.users.User.UserBuilder;
import com.gdiazs.bantui.users.UserRepository;

@Named
@Singleton
public class RegisterService {

  private static final int NOT_CONFIRMED = 0;

  private final UserRepository userRepository;

  private final PasswordHasher passwordHasher;

  private final ConfirmationTokenRepository confirmationTokenRepository;


  @Inject
  public RegisterService(final UserRepository userRepository,
      final PasswordHasher passwordHasher,
      final ConfirmationTokenRepository confirmationTokenRepository) throws RegisterUserException {

    this.userRepository = userRepository;
    this.passwordHasher = passwordHasher;
    this.confirmationTokenRepository = confirmationTokenRepository;

  }

  @Transactional
  public RegisterStatus createNewUser(final String username, final String email,
      final String password) throws RegisterUserException {

    if (this.userRepository.findByUsername(username).isPresent()) {
      return RegisterStatus.USERNAME_EXISTS;
    }
    if (this.userRepository.findByEmail(email).isPresent()) {
      return RegisterStatus.EMAIL_TAKEN;
    }

    final User user =
        new UserBuilder().addUsername(username).addPassword(this.passwordHasher.hash(password))
            .addRoles("ROLE_CONSUMER").addEmail(email).build();
    this.userRepository.save(user);
    return RegisterStatus.USER_CREATED;
  }



  @Transactional
  public ConfirmationToken createConfirmationToken(final String username) {

    final User foundUserByUsername = this.userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("Unknown user: " + username));


    final String uuid = UUID.randomUUID() + "";

    final ConfirmationTokenPK pk = new ConfirmationTokenPK();
    pk.setIdConfimationToken(uuid);
    pk.setIdUser(foundUserByUsername.getId());

    Date now = new Date();

    final ConfirmationToken confirmationToken = new ConfirmationToken();
    confirmationToken.setId(pk);
    confirmationToken.setConfirmed(NOT_CONFIRMED);
    confirmationToken.setToken(passwordHasher.hash(uuid + foundUserByUsername.getUsername()));
    confirmationToken.setCreatedAt(new Timestamp(now.getTime()));
    confirmationToken.setUpdatedAt(new Timestamp(now.getTime()));
    return this.confirmationTokenRepository.save(confirmationToken);
  }
  
  
  public void activateAccount(String username, String token) {
    
  }
}
