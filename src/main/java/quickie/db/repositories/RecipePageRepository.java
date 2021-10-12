package quickie.db.repositories;

import quickie.db.PersistenceManager;
import quickie.db.entities.RecipePage;

import javax.persistence.EntityManager;
import java.util.List;

public class RecipePageRepository {
    private final EntityManager em;

    public RecipePageRepository(EntityManager em) {
        this.em = em;
    }

    public List<RecipePage> readDirtyPages() {
        this.em.getTransaction().begin();
        List<RecipePage> pages = this.em.createQuery("SELECT r FROM RecipePage r WHERE r.dirty = true", RecipePage.class)
                .getResultList();
        this.em.getTransaction().commit();

        return pages;
    }

    public void save(RecipePage recipePage) {
        this.em.getTransaction().begin();
        this.em.persist(recipePage);
        this.em.getTransaction().commit();
    }

    public void remove(String url) {
        this.em.getTransaction().begin();
        this.em.createQuery("DELETE FROM RecipePage r WHERE r.url = '" + url + "'").executeUpdate();
        this.em.getTransaction().commit();
    }

    public void update(RecipePage recipePage) {
        this.em.getTransaction().begin();
        this.em.merge(recipePage);
        this.em.getTransaction().commit();
    }

    public boolean exists(String url) {
        this.em.getTransaction().begin();
        RecipePage pageEntity = em.find(RecipePage.class, url);
        this.em.getTransaction().commit();

        return pageEntity != null;
    }
}
