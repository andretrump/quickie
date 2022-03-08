package org.pinkcrazyunicorn.quickie.plugins.scraper.exceptions;

public class FailedToParseRecipe extends Exception {
    public FailedToParseRecipe() {
    }

    public FailedToParseRecipe(String message) {
        super(message);
    }
}
