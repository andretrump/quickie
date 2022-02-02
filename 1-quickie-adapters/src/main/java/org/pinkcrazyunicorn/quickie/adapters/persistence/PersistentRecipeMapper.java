package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Quantity;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PersistentRecipeMapper {
    private final PersistentRecipeFactory factory;

    public PersistentRecipeMapper(PersistentRecipeFactory factory) {
        this.factory = factory;
    }

    public PersistentRecipe mapToPersistent(Recipe recipe) {
        PersistentRecipe persistent = this.factory.createEmpty();

        persistent.setName(recipe.getName());
        persistent.setText(recipe.getText());
        persistent.setId(recipe.getId());

        List<String> foods = new ArrayList<>();
        List<String> units = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            foods.add(ingredient.getFood().getName());
            Quantity quantity = ingredient.getQuantity();
            units.add(quantity.getUnit().getName());
            amounts.add(quantity.getAmount());
        }
        persistent.setIngredientFoods(foods);
        persistent.setIngredientUnits(units);
        persistent.setIngredientAmounts(amounts);

        return persistent;
    }
}
