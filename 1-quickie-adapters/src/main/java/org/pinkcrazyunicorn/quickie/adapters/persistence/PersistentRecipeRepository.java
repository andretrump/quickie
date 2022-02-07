package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class PersistentRecipeRepository implements RecipeRepository {
    protected abstract void persistentRefreshRecipe(PersistentRecipe recipe);
    protected abstract Collection<? extends PersistentRecipe> persistentGetAll();
    protected abstract PersistentRecipe persistentGetBy(String origin);

    private final PersistentRecipeMapper mapper;

    public PersistentRecipeRepository(PersistentRecipeFactory factory) {
        mapper = new PersistentRecipeMapper(factory);
    }

    @Override
    public void refreshRecipe(Recipe recipe) {
        PersistentRecipe persistent = this.mapper.mapToPersistent(recipe);
        this.persistentRefreshRecipe(persistent);
    }

    @Override
    public Collection<Recipe> getAll() {
        return this.persistentGetAll().stream()
                .map(persistent -> this.mapper.mapFromPersistent(persistent))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> getBy(String origin) {
        PersistentRecipe persistent = this.persistentGetBy(origin);
        if (persistent == null) {
            return Optional.empty();
        }
        return Optional.of(this.mapper.mapFromPersistent(persistent));
    }
}
