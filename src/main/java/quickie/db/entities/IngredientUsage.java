package quickie.db.entities;

import quickie.db.types.Size;
import quickie.db.types.Unit;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_usage")
public class IngredientUsage {
    @EmbeddedId
    private IngredientUsageKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_name")
    private Ingredient ingredient;

    private double amount;
    private Size size;
    private Unit unit;

    public IngredientUsage() {}

    public IngredientUsage(IngredientUsageKey id, Recipe recipe, Ingredient ingredient, double amount, Size size, Unit unit) {
        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
        this.size = size;
        this.unit = unit;
    }

    public IngredientUsageKey getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public Size getSize() {
        return size;
    }

    public Unit getUnit() {
        return unit;
    }
}
