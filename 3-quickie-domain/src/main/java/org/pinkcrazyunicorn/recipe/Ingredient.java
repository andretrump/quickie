package org.pinkcrazyunicorn.recipe;

import org.pinkcrazyunicorn.Food;

public final class Ingredient {
    private final Food food;
    private final Quantity quantity;

    public Ingredient(Food food, Quantity quantity) {
        if (food == null) {
            throw new IllegalArgumentException("Food of Ingredient must be non-null");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity of Ingredient must be non-null");
        }
        this.food = food;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ingredient(" + food + ", " + quantity + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (!food.equals(that.food)) return false;
        return quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        int result = food.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
