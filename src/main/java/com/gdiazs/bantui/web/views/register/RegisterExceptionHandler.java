package com.gdiazs.bantui.web.views.register;

import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import com.gdiazs.bantui.web.security.exceptions.AccessDeniedException;
import io.gdiazs.bantui.commons.Messages;
import net.bootsfaces.utils.FacesMessages;

@ExceptionHandler
public class RegisterExceptionHandler {

  private Messages messages;


  public void handleELException(@Handles ExceptionEvent<AccessDeniedException> event) {
    notifyJSF(event.getException());
    event.handled();
  }

  private void notifyJSF(AccessDeniedException ex) {
    FacesMessages.error(messages.securityAccessDenied());
  }

}
