package com.gdiazs.bantui.register;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityExistsException;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.gdiazs.bantui.users.User;
import com.gdiazs.bantui.users.User.UserBuilder;
import com.gdiazs.bantui.users.UserRepository;

@Named
@Singleton
public class RegisterService {

	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Inject
	private Event<ExceptionToCatchEvent> exceptionEvent;

	@Inject
	public RegisterService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder)
			throws RegisterUserException {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void createNewUser(String username, String email, String password) throws RegisterUserException {

		//final User findByUsername = this.userRepository.findAnyByUsername(username);

		/**
		 * if(null != findByUsername) { throw new ExistingUserException("Username is
		 * already taken"); }
		 */
		User user = new UserBuilder().addUsername(username).addPassword(this.passwordEncoder.encode(password))
				.addRoles("ROLE_CONSUMER").addEmail(email).build();

		try {
			this.userRepository.save(user);
		} catch (EntityExistsException e) {
			exceptionEvent.fire(new ExceptionToCatchEvent(new RegisterUserException()));
		}

	}
}
