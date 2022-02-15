package org.pinkcrazyunicorn.quickie.adapters.mappers.recipe;

import org.pinkcrazyunicorn.quickie.adapters.event.*;
import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.Collection;

public class RecipeMapper {
    public EventAnswerData mapManyToEventAnswer(Collection<Recipe> recipes) {
        ListEventAnswerData result = new ListEventAnswerData();

        recipes.forEach(recipe -> result.add(this.mapToEventAnswer(recipe)));

        return result;
    }

    public EventAnswerData mapToEventAnswer(Recipe recipe) {
        MapEventAnswerData result = new MapEventAnswerData();

        result.put("name", new StringEventAnswerData(recipe.getName()));
        result.put("origin", new StringEventAnswerData(recipe.getOrigin()));

        ListEventAnswerData ingredients = new ListEventAnswerData();

        for (Ingredient ingredient : recipe.getIngredients()) {
            MapEventAnswerData ingredientData = new MapEventAnswerData();
            ingredientData.put("food", new StringEventAnswerData(ingredient.getFood().getName()));
            ingredientData.put("amount", new NumberEventAnswerData<Double>(ingredient.getQuantity().getAmount()));
            ingredientData.put("unit", new StringEventAnswerData(ingredient.getQuantity().getUnit().getName()));
            ingredients.add(ingredientData);
        }

        result.put("ingredients", ingredients);

        result.put("text", new StringEventAnswerData(recipe.getText()));

        return result;
    }
}
