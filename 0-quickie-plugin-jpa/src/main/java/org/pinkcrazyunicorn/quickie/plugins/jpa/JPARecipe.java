package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
public class JPARecipe implements PersistentRecipe {
    @Id
    UUID id;

    String text;
    String name;

    @ElementCollection
    List<String> foods;

    @ElementCollection
    List<String> units;

    @ElementCollection
    List<Double> amounts;

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
    public UUID getId() {
        return this.id;
    }

    @Override
    public List<String> getIngredientFoods() {
        return this.foods;
    }

    @Override
    public List<String> getIngredientUnits() {
        return this.units;
    }

    @Override
    public List<Double> getIngredientAmounts() {
        return this.amounts;
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
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void setIngredientFoods(List<String> foods) {
        this.foods = foods;
    }

    @Override
    public void setIngredientUnits(List<String> units) {
        this.units = units;
    }

    @Override
    public void setIngredientAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }
}
