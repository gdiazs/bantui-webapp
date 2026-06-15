package com.gdiazs.bantui.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ConfirmationTokenRepository {

  @PersistenceContext(unitName = "bantuiPu")
  EntityManager entityManager;

  public ConfirmationToken save(ConfirmationToken confirmationToken) {
    entityManager.persist(confirmationToken);
    return confirmationToken;
  }
}
