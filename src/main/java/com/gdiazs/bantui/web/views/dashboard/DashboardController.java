package com.gdiazs.bantui.web.views.dashboard;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DashboardController {

  private String input;

  @RolesAllowed("ROLE_ADMIN")
  public void click()
  {
      System.out.println("Corre");
  }

  public String getInput() {
      return input;
  }

  public void setInput(String input) {
      this.input = input;
  }
}
