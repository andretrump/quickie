package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;

import java.util.*;

public class Profile {
    private String name;
    private Set<Food> available;
    private Map<Food, Opinion> opinionsAbout;

    public Profile(String name, Set<Food> available, Map<Food, Opinion> opinionsAbout) {
        if (name == null) {
            throw new IllegalArgumentException("Name of Profile must be non-null");
        }
        if (available == null) {
            throw new IllegalArgumentException("Available food of Profile must be non-null");
        }
        if (opinionsAbout == null) {
            throw new IllegalArgumentException("Opinions of Profile must be non-null");
        }
        this.name = name;
        this.available = available;
        this.opinionsAbout = opinionsAbout;
    }

    public Profile(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name of Profile must be non-null");
        }
        this.name = name;
        this.available = new HashSet<>();
        this.opinionsAbout = new HashMap<>();
    }

    public void addOpinionAbout(Food food, Opinion opinion) {
        this.opinionsAbout.put(food, opinion);
    }

    public Optional<Opinion> getOpinionAbout(Food food) {
        Opinion maybeOpinion = this.opinionsAbout.get(food);
        if (maybeOpinion != null) {
            return Optional.of(maybeOpinion);
        }
        return Optional.empty();
    }

    public void addToAvailable(Food food) {
        this.available.add(food);
    }

    public boolean isAvailable(Food food) {
        return this.available.contains(food);
    }

    public void markUnavailable(Food food) {
        if (this.isAvailable(food)) {
            this.available.remove(food);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return name != null ? name.equals(profile.name) : profile.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Food> getAvailable() {
        return this.available;
    }

    public Map<Food, Opinion> getOpinions() {
        return this.opinionsAbout;
    }
}
