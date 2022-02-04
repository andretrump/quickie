package org.pinkcrazyunicorn.quickie.plugins.scraper;

import org.pinkcrazyunicorn.quickie.application.recipe.Datasource;
import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Quantity;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Henssler implements Datasource {
    public Henssler() {
    }

    @Override
    public Collection<Recipe> getRecipes() {
        // TODO: real implementation
        Ingredient ingredient1 = new Ingredient(new Food("Salz"), new Quantity(new Unit("Prise"), 8));
        Ingredient ingredient2 = new Ingredient(new Food("Nudeln"), new Quantity(new Unit("Gramm"), 500));
        Recipe recipe = new Recipe(List.of(ingredient1, ingredient2), "Nudeln mit Salz", "1. Nudeln kochen \n2. Nudeln abgießen \n" +
                "3. Nudeln abschrecken \n4. Nudeln in Schüssel füllen \n5. Nudeln salzen");
        return List.of(recipe);
    }
}
