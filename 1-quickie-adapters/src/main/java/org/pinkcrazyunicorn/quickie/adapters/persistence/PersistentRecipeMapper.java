package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Quantity;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.Unit;

import java.util.ArrayList;
import java.util.List;

public class PersistentRecipeMapper {
    private final PersistentRecipeFactory factory;

    public PersistentRecipeMapper(PersistentRecipeFactory factory) {
        this.factory = factory;
    }

    public PersistentRecipe mapToPersistent(Recipe recipe) {
        PersistentRecipe persistent = this.factory.createEmptyRecipe();

        persistent.setName(recipe.getName());
        persistent.setText(recipe.getText());
        persistent.setId(recipe.getId());

        List<PersistentIngredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            PersistentIngredient persistentIngredient = this.factory.createEmptyIngredient();
            persistentIngredient.setFood(ingredient.getFood().getName());
            Quantity quantity = ingredient.getQuantity();
            persistentIngredient.setUnit(quantity.getUnit().getName());
            persistentIngredient.setAmount(quantity.getAmount());
            ingredients.add(persistentIngredient);
        }
        persistent.setIngredients(ingredients);

        return persistent;
    }

    public Recipe mapFromPersistent(PersistentRecipe persistent) {
        List<? extends PersistentIngredient> persistentIngredients = persistent.getIngredients();

        List<Ingredient> ingredients = new ArrayList<>();
        for (PersistentIngredient persistentIngredient : persistentIngredients) {
            Food food = new Food(persistentIngredient.getFood());
            Unit unit = new Unit(persistentIngredient.getUnit());
            Quantity quantity = new Quantity(unit, persistentIngredient.getAmount());
            Ingredient ingredient = new Ingredient(food, quantity);
            ingredients.add(ingredient);
        }

        return new Recipe(ingredients, persistent.getName(), persistent.getText(), persistent.getId());
    }
}
