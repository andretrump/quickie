package quickie.db;

import quickie.db.entities.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeBuilder {
    private Set<Ingredient> ingredients;

    private Set<Step> steps;

    private String title;
    private int numberIngredients;
    private LocalDate date;
    private int numberPersons;
    private Duration videoLength;
    private int stars;
    private int numberRatings;

    private double energy;
    private double fat;
    private double carbohydrates;
    private double protein;
    private double fibre;

    public RecipeBuilder() {}

    public RecipeBuilder setIngredients(List<Ingredient> ingredients) {
        this.ingredients = new HashSet<>(ingredients);
        return this;
    }

    public RecipeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public RecipeBuilder setNumberIngredients(int numberIngredients) {
        this.numberIngredients = numberIngredients;
        return this;
    }

    public RecipeBuilder setSteps(List<Step> steps) {
        this.steps = new HashSet<>(steps);
        return this;
    }

    public RecipeBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public RecipeBuilder setNumberPersons(int numberPersons) {
        this.numberPersons = numberPersons;
        return this;
    }

    public RecipeBuilder setVideoLength(Duration videoLength) {
        this.videoLength = videoLength;
        return this;
    }

    public RecipeBuilder setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public RecipeBuilder setNumberRatings(int numberRatings) {
        this.numberRatings = numberRatings;
        return this;
    }

    public RecipeBuilder setEnergy(double energy) {
        this.energy = energy;
        return this;
    }

    public RecipeBuilder setFat(double fat) {
        this.fat = fat;
        return this;
    }

    public RecipeBuilder setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    public RecipeBuilder setProtein(double protein) {
        this.protein = protein;
        return this;
    }

    public RecipeBuilder setFibre(double fibre) {
        this.fibre = fibre;
        return this;
    }

    public Recipe build() {
        Recipe recipe = new Recipe(
                title,
                numberIngredients,
                date,
                numberPersons,
                videoLength,
                stars,
                numberRatings,
                energy,
                fat,
                carbohydrates,
                protein,
                fibre
        );
        recipe.setSteps(steps);
        return recipe;
    }
}
