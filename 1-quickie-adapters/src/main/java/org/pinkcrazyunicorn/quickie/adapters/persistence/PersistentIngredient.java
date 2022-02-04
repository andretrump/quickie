package org.pinkcrazyunicorn.quickie.adapters.persistence;

public interface PersistentIngredient {
    void setFood(String food);
    void setUnit(String unit);
    void setAmount(Double amount);

    String getFood();
    String getUnit();
    Double getAmount();
}
