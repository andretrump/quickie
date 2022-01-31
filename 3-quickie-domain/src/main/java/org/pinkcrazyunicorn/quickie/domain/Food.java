package org.pinkcrazyunicorn.quickie.domain;

public final class Food {
    private final String name;

    public Food(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of Food must be non-null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return getName().equals(food.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Food(" + name + ")";
    }
}
