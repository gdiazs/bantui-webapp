package com.gdiazs.bantui.web.views.register;

import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Param;
import com.gdiazs.bantui.commons.Messages;
import com.gdiazs.bantui.notification.NotificationService;
import com.gdiazs.bantui.register.RegisterService;
import com.gdiazs.bantui.register.RegisterStatus;
import net.bootsfaces.utils.FacesMessages;

@Named
@RequestScoped
public class RegisterController {

  @Inject
  private RegisterService registerService;

  @Inject
  private RegisterFormModel registerFormModel;

  @Inject
  private Messages messages;

  @Inject
  private NotificationService notificationService;
  
  @Inject @Param(name = "g-recaptcha-response")
  private String captcha;


  public void registerClick() {

    if (isValidPassword()) {

      System.out.println(captcha);
      RegisterStatus registerStatus =
          this.registerService.createNewUser(this.registerFormModel.getUsername(),
              this.registerFormModel.getEmail(), this.registerFormModel.getPassword());

      if (registerStatus == RegisterStatus.USERNAME_EXISTS) {
        
        FacesMessages.error(messages.getUserFound());

      }

      if (registerStatus == RegisterStatus.EMAIL_TAKEN) {
        
        FacesMessages.error(messages.getEmailFound());
      }

      if (registerStatus == RegisterStatus.USER_CREATED) {
        
        final Locale locale = getLocale();
        notificationService.sendConfirm(locale, this.registerFormModel.getUsername()  ,this.registerFormModel.getEmail());
        
        FacesMessages.info(messages.getAccountCreated());
      }

    }

  }

  private Locale getLocale() {
    return FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
  }

  private boolean isValidPassword() {
    return this.registerFormModel.getPassword().equals(this.registerFormModel.getRePassword());
  }
}
