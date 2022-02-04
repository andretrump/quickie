package org.pinkcrazyunicorn.quickie.adapters.persistence;

public interface PersistentRecipeFactory {
    PersistentRecipe createEmptyRecipe();
    PersistentIngredient createEmptyIngredient();
}
