package org.pinkcrazyunicorn.quickie.plugins.jpa;


import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentIngredient;

import javax.persistence.Embeddable;

@Embeddable
public class JPAIngredient implements PersistentIngredient {
    String food;
    String unit;
    Double amount;

    public JPAIngredient() {
    }

    public String getFood() {
        return food;
    }

    public String getUnit() {
        return unit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
