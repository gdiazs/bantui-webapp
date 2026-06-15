package com.gdiazs.bantui.startup;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.servlet.ServletContext;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@Dependent
public class EmailConfigProducer {
  
  @Inject
  private ServletContext context;

  @Produces
  @Singleton
  public ITemplateEngine templateEngine() {
    TemplateEngine templateEngine = new TemplateEngine();
    WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(
        JakartaServletWebApplication.buildApplication(this.context));
    
    templateResolver.setCacheable(false);

    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }
  
  
}
