package org.pinkcrazyunicorn.quickie.domain.recipe;

import java.util.Collection;
import java.util.UUID;

public class Recipe {
    private final Collection<Ingredient> ingredients;
    private final String text;
    private final String name;
    private final UUID id;

    public Recipe(Collection<Ingredient> ingredients, String name, String text, UUID id) {
        this.ingredients = ingredients;
        this.text = text;
        this.name = name;
        this.id = id;
    }

    public Recipe(Collection<Ingredient> ingredients, String name, String text) {
        this.ingredients = ingredients;
        this.text = text;
        this.name = name;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Recipe(" + name + ", " + id + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return id.equals(recipe.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
