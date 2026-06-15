package com.gdiazs.bantui.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {

  @PersistenceContext(unitName = "bantuiPu")
  EntityManager entityManager;

  public Optional<User> findByUsername(String username) {
    return entityManager.createQuery(
        "select user from User user where user.username = :username", User.class)
        .setParameter("username", username)
        .setMaxResults(1)
        .getResultStream()
        .findFirst();
  }

  public Optional<User> findByEmail(String email) {
    return entityManager.createQuery(
        "select user from User user where user.email = :email", User.class)
        .setParameter("email", email)
        .setMaxResults(1)
        .getResultStream()
        .findFirst();
  }

  public User save(User user) {
    entityManager.persist(user);
    return user;
  }
}
