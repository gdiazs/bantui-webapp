package com.gdiazs.bantui.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UserRepository {

  @Inject
  private EntityManager entityManager;

  public User findByUsername(String username) {
    return findAnyByUsername(username);
  }

  public User findAnyByUsername(String username) {
    return findFirstBy("username", username);
  }

  public User findAnyByEmail(String email) {
    return findFirstBy("email", email);
  }

  public User save(User user) {
    entityManager.persist(user);
    return user;
  }

  private User findFirstBy(String field, String value) {
    return entityManager.createQuery(
        "select user from User user where user." + field + " = :value", User.class)
        .setParameter("value", value)
        .setMaxResults(1)
        .getResultStream()
        .findFirst()
        .orElse(null);
  }
}
