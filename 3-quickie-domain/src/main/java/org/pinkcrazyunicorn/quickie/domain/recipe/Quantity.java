package org.pinkcrazyunicorn.quickie.domain.recipe;

public final class Quantity {
    private final Unit unit;
    private final double amount;


    public Quantity(Unit unit, double amount) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit of Quantity must be non-null");
        }
        this.unit = unit;
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity = (Quantity) o;

        if (Double.compare(quantity.amount, amount) != 0) return false;
        return unit.equals(quantity.unit);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = unit.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Quantity(" + amount + " " + unit.getName() + ")";
    }
}
