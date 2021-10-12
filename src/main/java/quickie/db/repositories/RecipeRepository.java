package quickie.db.repositories;

import quickie.db.entities.Recipe;

import javax.persistence.EntityManager;

public class RecipeRepository {
    private final EntityManager em;

    public RecipeRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Recipe recipe) {
        this.em.getTransaction().begin();
        this.em.persist(recipe);
        this.em.getTransaction().commit();
    }

    public void update(Recipe recipe) {
        this.em.getTransaction().begin();
        this.em.merge(recipe);
        this.em.getTransaction().commit();
    }
}
