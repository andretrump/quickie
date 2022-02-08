package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentIngredient;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JPARecipe implements PersistentRecipe {
    @Id
    String origin;

    @Lob
    String text;

    String name;

    @ElementCollection
    List<JPAIngredient> ingredients;

    public JPARecipe() {
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOrigin() {
        return this.origin;
    }

    @Override
    public List<JPAIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public void setIngredients(List<? extends PersistentIngredient> ingredients) {
        this.ingredients = new ArrayList<>();
        for (PersistentIngredient ingredient : ingredients) {
            if (!(ingredient instanceof JPAIngredient)) {
                System.out.println("Warning: JPARecipe only supports JPAIngredient. Skipping ingredient");
                continue;
            }
            this.ingredients.add((JPAIngredient) ingredient);
        }
    }
}
