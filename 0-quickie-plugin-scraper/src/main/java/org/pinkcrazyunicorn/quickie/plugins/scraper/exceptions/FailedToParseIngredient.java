package org.pinkcrazyunicorn.quickie.plugins.scraper.exceptions;

public class FailedToParseIngredient extends Exception {
    public FailedToParseIngredient() {
    }

    public FailedToParseIngredient(String message) {
        super(message);
    }
}
