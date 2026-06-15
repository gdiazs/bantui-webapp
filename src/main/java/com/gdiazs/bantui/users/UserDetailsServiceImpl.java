package com.gdiazs.bantui.users;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Named
@Singleton
public class UserDetailsServiceImpl implements UserDetailsService {

  @Inject
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final User findByUsername = userRepository.findByUsername(username);
    return findByUsername;
  }

}
