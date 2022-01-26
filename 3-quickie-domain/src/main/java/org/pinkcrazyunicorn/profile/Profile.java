package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class Profile {
    private String name;
    private Stock stock;
    private Collection<OpinionAbout> opinions;

    public Profile(String name, Stock stock, Collection<OpinionAbout> opinions) {
        if (name == null) {
            throw new IllegalArgumentException("Name of Profile must be non-null");
        }
        if (stock == null) {
            throw new IllegalArgumentException("Stock of Profile must be non-null");
        }
        if (opinions == null) {
            throw new IllegalArgumentException("Opinions of Profile must be non-null");
        }
        this.name = name;
        this.stock = stock;
        this.opinions = opinions;
    }

    public void addOpinionAbout(Opinion opinion, Food food) {
        Optional<OpinionAbout> existingOpinion = this.getOpinionAbout(food);
        if (existingOpinion.isPresent()) {
            this.opinions.remove(existingOpinion.get());
        }
        this.opinions.add(new OpinionAbout(food, opinion));
    }

    public Optional<OpinionAbout> getOpinionAbout(Food food) {
        for (OpinionAbout opinion : this.opinions) {
            if (opinion.isAbout(food)) {
                return Optional.of(opinion);
            }
        }
        return Optional.empty();
    }

    public void addToStock(Food food) {
        this.stock.add(food);
    }

    public boolean isInStock(Food food) {
        return this.stock.has(food);
    }

    public void removeFromStock(Food food) {
        this.stock.remove(food);
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

    public Stock getStock() {
        return this.stock;
    }

    public Collection<OpinionAbout> getOpinions() {
        return this.opinions;
    }
}
