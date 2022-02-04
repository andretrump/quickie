package org.pinkcrazyunicorn.quickie.adapters.persistence;

import java.util.List;
import java.util.UUID;

public interface PersistentRecipe {
    String getText();
    String getName();
    UUID getId();
    List<? extends PersistentIngredient> getIngredients();

    void setText(String text);
    void setName(String name);
    void setId(UUID id);
    void setIngredients(List<? extends PersistentIngredient> ingredients);
}
