package org.pinkcrazyunicorn.quickie.application.recipe;

import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

import java.util.Collection;
import java.util.Optional;

public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public void refreshFrom(Datasource source) {
        Collection<Recipe> recipes = source.getRecipes();
        for (Recipe recipe : recipes) {
            this.repository.refreshRecipe(recipe);
        }
    }

    public Collection<Recipe> getAll() {
        return this.repository.getAll();
    }

    public Optional<Recipe> getBy(String origin) {
        return this.repository.getBy(origin);
    }
}
