package com.gdiazs.bantui.spring;

import jakarta.enterprise.inject.spi.CDI;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class CdiLocatorFactoryBean implements InitializingBean, FactoryBean<Object> {

  private Class<?> businessInterface;

  private Class<?> businessImplementation;

  private Object cdiBean;



  @Override
  public Object getObject() throws Exception {
    return this.cdiBean;
  }

  @Override
  public Class<?> getObjectType() {
    return businessInterface;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  public Class<?> getBusinessInterface() {
    return businessInterface;
  }

  public void setBusinessInterface(Class<?> businessInterface) {
    this.businessInterface = businessInterface;
  }


  public Class<?> getBusinessImplementation() {
    return businessImplementation;
  }

  public void setBusinessImplementation(Class<?> businessImplementation) {
    this.businessImplementation = businessImplementation;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.cdiBean = CDI.current().select(this.businessImplementation).get();
  }



}
