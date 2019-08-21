package com.gdiazs.bantui.web.views.register;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.gdiazs.bantui.register.RegisterService;

@Named
@RequestScoped
public class RegisterController {

  @Inject
  private RegisterService registerService;

  @Inject
  private RegisterFormModel registerFormModel;

  public void registerClick() {

    if (isValidPassword()) {

      this.registerService.createNewUser(this.registerFormModel.getUsername(),
          this.registerFormModel.getEmail(), this.registerFormModel.getPassword());

    }

  }

  private boolean isValidPassword() {
    return this.registerFormModel.getPassword().equals(this.registerFormModel.getRePassword());
  }
}
