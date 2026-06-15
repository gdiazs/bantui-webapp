package com.gdiazs.bantui.email;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mail.Session;
import jakarta.ws.rs.NotAcceptableException;
import org.apache.commons.mail2.core.EmailException;
import org.apache.commons.mail2.jakarta.HtmlEmail;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import com.gdiazs.bantui.register.RegisterUserException;

@Named
@Singleton
public class ThymeleafCommonsMail implements EmailSender {


  @Inject
  private ITemplateEngine templateEngine;

  @Resource(name = "jndi/defaultMail")
  private Session mailSession;

  private String template;

  private Map<String, Object> variables = null;



  @Override
  public void loadTemplate(final String templatePath) {
    this.template = templatePath;

  }

  @Override
  public void addVariables(final Map<String, Object> variables) {
    this.variables = variables;

  }

  @Override
  public void send(Locale locale, String subject, String from, String... to)
      throws EmailSenderException {
    throw new NotAcceptableException();

  }

  @Override
  public void sendAsync(Locale locale, String subject, String from, String... to)
      throws EmailSenderException {

    final CommonsMailBuilder mailBuilder = new CommonsMailBuilder();

    final Context context = new Context();

    context.setLocale(locale);
    context.setVariables(variables);

    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

    CompletableFuture.supplyAsync(() -> {

      final String parsedTemplate = this.templateEngine.process(this.template, context);

      return parsedTemplate;


    }, newScheduledThreadPool).thenAcceptAsync((parsedTemplate) -> {

      HtmlEmail email;
      try {
        email = mailBuilder.addMailSession(mailSession).addFrom(from).addTo(to).addSubject(subject)
            .addHtmlMsg(parsedTemplate).build();

        email.send();

      } catch (EmailException ex) {
        ex.printStackTrace();
        throw new RegisterUserException("Error sending email", ex.getCause());
      }

    }, newScheduledThreadPool).whenComplete((s, e) -> {

    });


  }



}
