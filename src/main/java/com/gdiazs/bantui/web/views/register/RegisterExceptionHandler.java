package com.gdiazs.bantui.web.views.register;

import javax.persistence.PersistenceException;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import com.gdiazs.bantui.web.security.AccessDeniedException;
import io.gdiazs.bantui.commons.Messages;
import net.bootsfaces.utils.FacesMessages;

@ExceptionHandler
public class RegisterExceptionHandler {

  private Messages messages;


  public void onELException(@Handles ExceptionEvent<AccessDeniedException> event) {
    FacesMessages.error(messages.securityAccessDenied());
    event.handled();
  }
  
  public void onPersistenceException(@Handles ExceptionEvent<PersistenceException> event) {
    event.handled();
  }


}
