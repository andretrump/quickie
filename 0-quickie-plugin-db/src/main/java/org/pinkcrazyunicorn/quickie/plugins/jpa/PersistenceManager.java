package org.pinkcrazyunicorn.quickie.plugins.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static PersistenceManager INSTANCE = null;
    private final EntityManagerFactory entityManagerFactory;

    private PersistenceManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("quickie");
    }

    public static PersistenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceManager();
        }
        return INSTANCE;
    }

    public EntityManager getManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}
