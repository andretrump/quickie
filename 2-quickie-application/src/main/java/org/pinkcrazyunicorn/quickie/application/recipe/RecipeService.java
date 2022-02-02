package org.pinkcrazyunicorn.quickie.application.recipe;

import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public void refreshFrom(Datasource source) {
        for (Recipe recipe : source.getRecipes()) {
            this.repository.refreshRecipe(recipe);
        }
    }
}
