package org.pinkcrazyunicorn.quickie.adapters.persistence;

import java.util.List;
import java.util.UUID;

public interface PersistentRecipe {
    String getText();
    String getName();
    UUID getId();
    List<String> getIngredientFoods();
    List<String> getIngredientUnits();
    List<Double> getIngredientAmounts();

    void setText(String text);
    void setName(String name);
    void setId(UUID id);
    void setIngredientFoods(List<String> foods);
    void setIngredientUnits(List<String> units);
    void setIngredientAmounts(List<Double> amounts);
}
