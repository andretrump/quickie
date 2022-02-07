package org.pinkcrazyunicorn.quickie.domain.recipe;

import java.util.Collection;
import java.util.UUID;

public class Recipe {
    private final Collection<Ingredient> ingredients;
    private final String text;
    private final String name;
    private final String origin;

    public Recipe(Collection<Ingredient> ingredients, String name, String text, String origin) {
        this.ingredients = ingredients;
        this.text = text;
        this.name = name;
        this.origin = origin;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Recipe(" + name + ", " + origin + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return origin.equals(recipe.origin);
    }

    @Override
    public int hashCode() {
        return origin.hashCode();
    }
}
