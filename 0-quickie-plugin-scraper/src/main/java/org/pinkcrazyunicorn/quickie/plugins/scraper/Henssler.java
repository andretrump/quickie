package org.pinkcrazyunicorn.quickie.plugins.scraper;

import org.pinkcrazyunicorn.quickie.application.recipe.Datasource;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.ArrayList;
import java.util.Collection;

public class Henssler implements Datasource {
    public Henssler() {
    }

    @Override
    public Collection<Recipe> getRecipes() {
        // TODO
        return new ArrayList<>();
    }
}
