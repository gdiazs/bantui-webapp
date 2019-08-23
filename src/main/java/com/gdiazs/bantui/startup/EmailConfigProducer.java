package com.gdiazs.bantui.startup;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Dependent
public class EmailConfigProducer {
  
  @Inject
  private ServletContext context;

  @Produces
  @Singleton
  public ITemplateEngine templateEngine() {
    TemplateEngine templateEngine = new TemplateEngine();
    ServletContextTemplateResolver templateResolver = 
        new ServletContextTemplateResolver(this.context);
    
    templateResolver.setCacheable(false);

    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }
  
  
}
