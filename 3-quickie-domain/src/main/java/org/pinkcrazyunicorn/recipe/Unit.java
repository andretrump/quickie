package org.pinkcrazyunicorn.recipe;

public final class Unit {
    private final String name;

    public Unit(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of Unit must be non-null");
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

        Unit unit = (Unit) o;

        return name.equals(unit.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Unit(" + name + ")";
    }
}
