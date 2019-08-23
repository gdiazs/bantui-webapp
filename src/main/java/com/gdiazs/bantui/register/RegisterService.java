package com.gdiazs.bantui.register;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

  private final BCryptPasswordEncoder passwordEncoder;

  private final ConfirmationTokenRepository confirmationTokenRepository;


  @Inject
  public RegisterService(final UserRepository userRepository,
      final BCryptPasswordEncoder passwordEncoder,
      final ConfirmationTokenRepository confirmationTokenRepository) throws RegisterUserException {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.confirmationTokenRepository = confirmationTokenRepository;

  }

  @Transactional
  public RegisterStatus createNewUser(final String username, final String email,
      final String password) throws RegisterUserException {

    final User foundUserByusername = this.userRepository.findAnyByUsername(username);
    final User foundUserByEmail = this.userRepository.findAnyByEmail(email);


    RegisterStatus registerStatus = RegisterStatus.USER_NOT_CREATED;

    if (foundUserByusername == null || foundUserByEmail == null) {
      final User user =
          new UserBuilder().addUsername(username).addPassword(this.passwordEncoder.encode(password))
              .addRoles("ROLE_CONSUMER").addEmail(email).build();

      this.userRepository.save(user);

      registerStatus = RegisterStatus.USER_CREATED;
    }

    if (foundUserByusername != null) {
      registerStatus = RegisterStatus.USERNAME_EXISTS;

    }

    if (foundUserByEmail != null) {
      registerStatus = RegisterStatus.EMAIL_TAKEN;

    }

    return registerStatus;
  }



  @Transactional
  public ConfirmationToken createConfirmationToken(final String username) {

    final User foundAnyByUsername = this.userRepository.findAnyByUsername(username);


    final String uuid = UUID.randomUUID() + "";

    final ConfirmationTokenPK pk = new ConfirmationTokenPK();
    pk.setIdConfimationToken(uuid);
    pk.setIdUser(foundAnyByUsername.getId());

    Date now = new Date();

    final ConfirmationToken confirmationToken = new ConfirmationToken();
    confirmationToken.setId(pk);
    confirmationToken.setConfirmed(NOT_CONFIRMED);
    confirmationToken.setToken(passwordEncoder.encode(uuid + foundAnyByUsername.getUsername()));
    confirmationToken.setCreatedAt(new Timestamp(now.getTime()));
    confirmationToken.setUpdatedAt(new Timestamp(now.getTime()));
    return this.confirmationTokenRepository.save(confirmationToken);
  }
  
  
  public void activateAccount(String username, String token) {
    
  }
}
