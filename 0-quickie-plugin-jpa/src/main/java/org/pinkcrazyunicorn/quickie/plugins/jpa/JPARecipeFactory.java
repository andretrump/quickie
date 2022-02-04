package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentIngredient;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipeFactory;

public class JPARecipeFactory implements PersistentRecipeFactory {

    @Override
    public PersistentRecipe createEmptyRecipe() {
        return new JPARecipe();
    }

    @Override
    public PersistentIngredient createEmptyIngredient() {
        return new JPAIngredient();
    }
}
