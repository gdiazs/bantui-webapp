package com.gdiazs.bantui.startup;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Named
@Singleton
public class EntityManagerProducer {

    @PersistenceUnit(unitName = "bantuiPu")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    public EntityManager create()
    {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em)
    {
        if (em.isOpen())
        {
            em.close();
        }
    }
}
