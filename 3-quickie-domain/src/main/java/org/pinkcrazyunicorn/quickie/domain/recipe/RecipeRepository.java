package org.pinkcrazyunicorn.quickie.domain.recipe;

import java.util.Collection;
import java.util.Optional;

public interface RecipeRepository {
    void refreshRecipe(Recipe recipe);
    Collection<Recipe> getAll();

    Optional<Recipe> getBy(String origin);
}
