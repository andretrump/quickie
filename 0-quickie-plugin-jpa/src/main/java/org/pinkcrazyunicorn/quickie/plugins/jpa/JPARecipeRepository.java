package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.UUID;

public class JPARecipeRepository extends PersistentRecipeRepository {
    private final EntityManager entityManager;

    public JPARecipeRepository(EntityManager entityManager) {
        super(new JPARecipeFactory());
        this.entityManager = entityManager;
    }

    @Override
    protected void persistentRefreshRecipe(PersistentRecipe recipe) {
        // check existing record
        if (!(recipe instanceof JPARecipe)) {
            throw new IllegalArgumentException("JPARecipeRepository only supports JPARecipes");
        }
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        // TODO better identification of identical recipe
        try {
            PersistentRecipe existing = this.entityManager.find(JPARecipe.class, recipe.getId());
            if (existing != null) {
                this.entityManager.merge(recipe);
            } else {
                this.entityManager.persist(recipe);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    protected Collection<? extends PersistentRecipe> persistentGetAll() {
        TypedQuery<JPARecipe> query = this.entityManager.createQuery("SELECT r FROM JPARecipe r join fetch r.ingredients", JPARecipe.class);
        return query.getResultList();
    }

    @Override
    protected PersistentRecipe persistentGetBy(UUID id) {
        return this.entityManager.find(JPARecipe.class, id);
    }
}
