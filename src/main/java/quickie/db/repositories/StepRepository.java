package quickie.db.repositories;

import quickie.db.entities.Step;

import javax.persistence.EntityManager;

public class StepRepository {
    private final EntityManager em;

    public StepRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Step step) {
        this.em.getTransaction().begin();
        this.em.persist(step);
        this.em.getTransaction().commit();
    }

    public void update(Step step) {
        this.em.getTransaction().begin();
        this.em.merge(step);
        this.em.getTransaction().commit();
    }
}
