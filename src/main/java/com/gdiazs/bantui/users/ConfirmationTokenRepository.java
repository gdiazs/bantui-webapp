package com.gdiazs.bantui.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ConfirmationTokenRepository {

  @Inject
  private EntityManager entityManager;

  public ConfirmationToken save(ConfirmationToken confirmationToken) {
    entityManager.persist(confirmationToken);
    return confirmationToken;
  }
}
