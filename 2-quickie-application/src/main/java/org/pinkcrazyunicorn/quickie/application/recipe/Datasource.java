package org.pinkcrazyunicorn.quickie.application.recipe;

import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.Collection;

public interface Datasource {
    Collection<Recipe> getRecipes();
}
