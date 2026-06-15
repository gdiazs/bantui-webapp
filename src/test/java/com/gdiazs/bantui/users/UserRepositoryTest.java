package com.gdiazs.bantui.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

  private EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;
  private UserRepository repository;

  @BeforeEach
  void setUp() {
    entityManagerFactory = Persistence.createEntityManagerFactory("bantuiTestPu");
    entityManager = entityManagerFactory.createEntityManager();
    repository = new UserRepository();
    repository.entityManager = entityManager;
  }

  @AfterEach
  void tearDown() {
    if (entityManager != null) {
      entityManager.close();
    }
    if (entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  @Test
  void savesAndFindsUsersWithExplicitQueries() {
    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setUsername("repository-user");
    user.setEmail("repository@example.com");
    user.setPassword("encoded");

    entityManager.getTransaction().begin();
    repository.save(user);
    entityManager.getTransaction().commit();
    entityManager.clear();

    assertEquals(user.getId(), repository.findByUsername("repository-user").orElseThrow().getId());
    assertEquals(user.getId(),
        repository.findByEmail("repository@example.com").orElseThrow().getId());
    assertTrue(repository.findByUsername("missing-user").isEmpty());
  }
}
