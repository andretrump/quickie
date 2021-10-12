package quickie.db.repositories;

import quickie.db.entities.IngredientUsage;

import javax.persistence.EntityManager;

public class IngredientUsageRepository {
    private final EntityManager em;

    public IngredientUsageRepository(EntityManager em) {
        this.em = em;
    }

    public void save(IngredientUsage usage) {
        this.em.getTransaction().begin();
        this.em.persist(usage);
        this.em.getTransaction().commit();
    }

    public void update(IngredientUsage usage) {
        this.em.getTransaction().begin();
        this.em.merge(usage);
        this.em.getTransaction().commit();
    }
}
