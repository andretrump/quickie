package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;

import java.util.Collection;

public class Stock {
    private Collection<Food> foodInStock;

    public Stock(Collection<Food> foodInStock) {
        this.foodInStock = foodInStock;
    }

    public void add(Food food) {
        this.foodInStock.add(food);
    }

    public void remove(Food food) {
        while (this.foodInStock.contains(food)) {
            this.foodInStock.remove(food);
        }
    }

    public boolean has(Food food) {
        return this.foodInStock.contains(food);
    }
}
