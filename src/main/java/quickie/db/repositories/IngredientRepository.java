package quickie.db.repositories;

import org.hibernate.exception.ConstraintViolationException;
import quickie.db.exceptions.EntityNotFound;
import quickie.db.entities.Ingredient;
import quickie.db.exceptions.IngredientNameExists;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class IngredientRepository {
    private final EntityManager em;

    public IngredientRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Ingredient ingredient) throws IngredientNameExists {
        try {
            this.em.getTransaction().begin();
            this.em.persist(ingredient);
            this.em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                em.getTransaction().rollback();
                throw new IngredientNameExists();
            } else {
                throw new PersistenceException();
            }
        }
    }

    public void update(Ingredient ingredient) {
        this.em.getTransaction().begin();
        this.em.merge(ingredient);
        this.em.getTransaction().commit();
    }

    public Ingredient read(String name) throws EntityNotFound {
        this.em.getTransaction().begin();
        TypedQuery<Ingredient> query = this.em.createQuery("SELECT i FROM Ingredient i WHERE i.name = ?1", Ingredient.class);
        Ingredient ingredientEntity = query.setParameter(1, name).getSingleResult();
        this.em.getTransaction().commit();

        if (ingredientEntity == null) {
            throw new EntityNotFound();
        }
        return ingredientEntity;
    }

    public boolean exists(String name) {
        this.em.getTransaction().begin();
        Ingredient ingredientEntity =  this.em.find(Ingredient.class, name);
        this.em.getTransaction().commit();

        return ingredientEntity != null;
    }
}
