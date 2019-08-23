package com.gdiazs.bantui.notification;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import com.gdiazs.bantui.email.EmailSender;
import com.gdiazs.bantui.email.EmailTemplate;
import com.gdiazs.bantui.register.RegisterService;
import com.gdiazs.bantui.users.ConfirmationToken;

@Named
@Singleton
public class NotificationService {


  private final RegisterService registerService;
  private final EmailSender emailSender;


  @Inject
  public NotificationService(EmailSender emailSender, RegisterService registerService) {
    this.emailSender = emailSender;
    this.registerService = registerService;
  }

  public void sendConfirm(Locale locale, String username, String... emails) {

    ConfirmationToken createConfirmationToken = registerService.createConfirmationToken(username);

    final Map<String, Object> variables = new HashMap<>();
    variables.put("username", username);
    variables.put("url", "http://localhost:8080/bantui/pages/activateAccount.xhtml?token=" + createConfirmationToken.getToken());
    variables.put("imageurl","http://localhost:8080/bantui/javax.faces.resource/logo.png.xhtml?ln=images");
    
    emailSender.loadTemplate(EmailTemplate.CONFIRMATION_TEMPLATE.getValue());
    emailSender.addVariables(variables);
    emailSender.sendAsync(locale, "Activación de cuenta Bantui", "diazgbs@gmail.com", emails);
  }
}
