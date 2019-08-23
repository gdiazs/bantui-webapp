package com.gdiazs.bantui.users;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface ConfirmationTokenRepository
    extends EntityRepository<ConfirmationToken, ConfirmationTokenPK> {

}
