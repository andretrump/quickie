package quickie.webscraping;

import quickie.db.entities.Ingredient;
import quickie.db.types.Size;
import quickie.db.types.Unit;

public class RecipeIngredient {
    private String name;
    private double amount;
    private Size size;
    private Unit unit;

    public RecipeIngredient(String name, double amount, Size size, Unit unit) {
        this.name = name;
        this.amount = amount;
        this.size = size;
        this.unit = unit;
    }

    public String getName() {
        return name;
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

    public Ingredient toIngredient() {
        return new Ingredient(this.name);
    }
}
