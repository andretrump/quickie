package org.pinkcrazyunicorn.quickie.domain.recipe;

import java.util.Collection;

public interface RecipeRepository {
    void refreshRecipe(Recipe recipe);
    Collection<Recipe> getAll();
}
