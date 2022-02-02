package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipe;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentRecipeFactory;

public class JPARecipeFactory implements PersistentRecipeFactory {
    @Override
    public PersistentRecipe createEmpty() {
        return new JPARecipe();
    }
}
