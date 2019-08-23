package com.gdiazs.bantui.web.views.dashboard;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.gdiazs.bantui.web.security.Secured;

@Named
@RequestScoped
public class DashboardController {

  private String input;

  @Secured({"ROLE_ADMIN"})
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
