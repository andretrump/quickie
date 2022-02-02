package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipeRepository;

import javax.persistence.EntityManager;

public class JPARecipeRepository extends PersistentRecipeRepository {
    private final EntityManager entityManager;

    public JPARecipeRepository(EntityManager entityManager) {
        super(new JPARecipeFactory());
        this.entityManager = entityManager;
    }

    @Override
    protected void persistentRefreshRecipe(PersistentRecipe recipe) {
        // TODO
    }
}
