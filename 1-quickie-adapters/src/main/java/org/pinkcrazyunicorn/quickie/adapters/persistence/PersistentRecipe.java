package org.pinkcrazyunicorn.quickie.adapters.persistence;

import java.util.List;

public interface PersistentRecipe {
    String getText();
    String getName();
    String getOrigin();
    List<? extends PersistentIngredient> getIngredients();

    void setText(String text);
    void setName(String name);
    void setOrigin(String origin);
    void setIngredients(List<? extends PersistentIngredient> ingredients);
}
