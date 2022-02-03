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

    public Recipe mapFromPersistent(PersistentRecipe persistent) {
        List<String> foods = persistent.getIngredientFoods();
        List<String> units = persistent.getIngredientUnits();
        List<Double> amounts = persistent.getIngredientAmounts();
        assert amounts.size() == units.size();
        assert foods.size() == units.size();

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            Food food = new Food(foods.get(i));
            Unit unit = new Unit(units.get(i));
            Quantity quantity = new Quantity(unit, amounts.get(i));
            Ingredient ingredient = new Ingredient(food, quantity);
            ingredients.add(ingredient);
        }

        return new Recipe(ingredients, persistent.getName(), persistent.getText(), persistent.getId());
    }
}
