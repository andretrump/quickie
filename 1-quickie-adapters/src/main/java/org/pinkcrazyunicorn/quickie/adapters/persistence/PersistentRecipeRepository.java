package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

public abstract class PersistentRecipeRepository implements RecipeRepository {
    protected abstract void persistentRefreshRecipe(PersistentRecipe recipe);

    private final PersistentRecipeMapper mapper;

    public PersistentRecipeRepository(PersistentRecipeFactory factory) {
        mapper = new PersistentRecipeMapper(factory);
    }

    @Override
    public void refreshRecipe(Recipe recipe) {
        PersistentRecipe persistent = this.mapper.mapToPersistent(recipe);
        this.persistentRefreshRecipe(persistent);
    }
}
