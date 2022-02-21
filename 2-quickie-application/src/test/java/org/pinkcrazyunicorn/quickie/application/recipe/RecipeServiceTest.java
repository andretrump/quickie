package org.pinkcrazyunicorn.quickie.application.recipe;

import static org.assertj.core.api.Assertions.*;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class RecipeServiceTest {
    @Test
    public void shouldGetByOrigin() {
        Recipe recipe = EasyMock.createMock(Recipe.class);
        EasyMock.replay(recipe);
        RecipeRepository repository = EasyMock.createMock(RecipeRepository.class);
        EasyMock.expect(repository.getBy("Default")).andReturn(Optional.of(recipe));
        EasyMock.replay(repository);

        RecipeService codeUnderTest = new RecipeService(repository);

        Optional<Recipe> actual = codeUnderTest.getBy("Default");

        assertThat(actual).hasValue(recipe);
    }

    @Test
    public void shouldNotGetByInvalidOrigin() {
        RecipeRepository repository = EasyMock.createMock(RecipeRepository.class);
        EasyMock.expect(repository.getBy("Invalid")).andReturn(Optional.empty());
        EasyMock.replay(repository);

        RecipeService codeUnderTest = new RecipeService(repository);

        Optional<Recipe> actual = codeUnderTest.getBy("Invalid");

        assertThat(actual).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getRecipeData")
    public void shouldGetAll(List<Recipe> recipes) {
        RecipeRepository repository = EasyMock.createMock(RecipeRepository.class);
        EasyMock.expect(repository.getAll()).andReturn(recipes);

        EasyMock.replay(repository);
        RecipeService codeUnderTest = new RecipeService(repository);

        Collection<Recipe> actual = codeUnderTest.getAll();

        assertThat(actual).hasSize(recipes.size());
        for (Recipe recipe : recipes) {
            assertThat(actual).contains(recipe);
            EasyMock.verify(recipe);
        }
        EasyMock.verify(repository);
    }

    @ParameterizedTest
    @MethodSource("getRecipeData")
    public void shouldRefreshAll(List<Recipe> recipes) {
        RecipeRepository repository = EasyMock.createStrictMock(RecipeRepository.class);
        Datasource source = EasyMock.createMock(Datasource.class);
        EasyMock.expect(source.getRecipes()).andReturn(recipes);
        for (Recipe recipe : recipes) {
            repository.refreshRecipe(recipe);
        }
        EasyMock.replay(repository);
        EasyMock.replay(source);

        RecipeService codeUnderTest = new RecipeService(repository);

        codeUnderTest.refreshFrom(source);

        for (Recipe recipe : recipes) {
            EasyMock.verify(recipe);
        }
        EasyMock.verify(repository);
        EasyMock.verify(source);
    }

    static Stream<Arguments> getRecipeData() {
        Recipe mock = EasyMock.createMock(Recipe.class);
        EasyMock.replay(mock);
        return Stream.of(
            Arguments.of(List.of()),
            Arguments.of(List.of(mock)),
            Arguments.of(List.of(mock, mock, mock)),
            Arguments.of(List.of(mock, mock, mock, mock))
        );
    }
}
