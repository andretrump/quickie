package org.pinkcrazyunicorn.quickie.application.recipe;

import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;
import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;

import java.util.*;
import java.util.stream.Collectors;

public class MatchingService {
    private final RecipeService recipeService;
    private final RecipeRepository repository;

    public MatchingService(RecipeService recipeService, RecipeRepository repository) {
        super();
        this.recipeService = recipeService;
        this.repository = repository;
    }

    public Collection<Recipe> getMatchingRecipesFor(String origin, Profile profile) {
        Optional<Recipe> maybeRecipe = this.repository.getBy(origin);
        if (maybeRecipe.isEmpty()) {
            System.out.println("Warning: Could not find recipe");
            return List.of();
        }

        Recipe recipe = maybeRecipe.get();

        List<Map.Entry<Recipe, Double>> ratedRecipes = new ArrayList<>();
        for (Recipe comparing : this.recipeService.getAll()) {
            double value = this.compare(recipe, comparing, profile);
            Map.Entry<Recipe, Double> entry = new AbstractMap.SimpleEntry<>(comparing, value);
            ratedRecipes.add(entry);
        }

        ratedRecipes.sort(Map.Entry.comparingByValue());
        Collections.reverse(ratedRecipes);
        return ratedRecipes.stream()
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double compare(Recipe recipe, Recipe comparing, Profile profile) {
        if (this.containsDealbreaker(comparing, profile)) {
            return Double.NEGATIVE_INFINITY;
        }

        double iou = this.getIntersectionOverUnion(recipe, comparing);
        double opinionScore = this.getOpinionScore(comparing, profile);

        return iou + opinionScore;
    }

    private double getOpinionScore(Recipe comparing, Profile profile) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        for (Ingredient ingredient : comparing.getIngredients()) {
            Opinion opinion = profile.getOpinionAbout(ingredient.getFood()).orElse(Opinion.Indifferent);
            double value = opinion.getWeight();
            max = Double.max(max, value);
            min = Double.min(min, value);
        }

        if (Double.isInfinite(max) || Double.isInfinite(min)) {
            return 0;
        }
        return (max + min) / Opinion.Dislike.getWeight();
    }

    private double getIntersectionOverUnion(Recipe recipe, Recipe comparing) {
        Set<Food> recipeIngredients = recipe.getIngredients()
                .stream()
                .map(Ingredient::getFood)
                .collect(Collectors.toSet());
        Set<Food> comparingIngredients = comparing.getIngredients()
                .stream()
                .map(Ingredient::getFood)
                .collect(Collectors.toSet());

        Set<Food> intersection = new HashSet<>(recipeIngredients);
        intersection.retainAll(comparingIngredients);

        Set<Food> union = new HashSet<>(recipeIngredients);
        union.addAll(comparingIngredients);

        double intersectionSize = intersection.size();
        double unionSize = union.size();
        return intersectionSize / unionSize;
    }

    private boolean containsDealbreaker(Recipe comparing, Profile profile) {
        for (Ingredient ingredient : comparing.getIngredients()) {
            Optional<Opinion> maybeOpinion = profile.getOpinionAbout(ingredient.getFood());
            if (maybeOpinion.isEmpty()) {
                continue;
            }
            Opinion opinion = maybeOpinion.get();
            if (opinion.equals(Opinion.Dealbreaker)) {
                return true;
            }
        }
        return false;
    }
}
