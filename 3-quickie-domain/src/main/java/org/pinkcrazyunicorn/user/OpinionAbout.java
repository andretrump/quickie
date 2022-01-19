package org.pinkcrazyunicorn.user;

import org.pinkcrazyunicorn.Food;

public final class OpinionAbout {
    private final Food food;
    private final Opinion opinion;

    public OpinionAbout(Food food, Opinion opinion) {
        if (food == null) {
            throw new IllegalArgumentException("Food of OpinionAbout must be non-null");
        }
        if (opinion == null) {
            throw new IllegalArgumentException("Opinion of OpinionAbout must be non-null");
        }
        this.food = food;
        this.opinion = opinion;
    }
}
