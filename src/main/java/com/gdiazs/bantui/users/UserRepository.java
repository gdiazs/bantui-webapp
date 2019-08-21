package com.gdiazs.bantui.users;

import javax.persistence.PersistenceException;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface UserRepository extends EntityRepository<User, String> {

  public User findByUsername(String username);

  public User findAnyByUsername(String username) throws PersistenceException;
}
