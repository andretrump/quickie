package quickie.download;

import quickie.db.types.RecipeCategory;

public enum HensslerPage {
    SALAD("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/salat-rezepte-1", RecipeCategory.SALAD),
    MEAT("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/fleischgerichte-2", RecipeCategory.MEAT),
    VEGETARIAN("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/vegetarische-rezepte-3", RecipeCategory.VEGETARIAN),
    PASTA("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/pasta-rezepte-4", RecipeCategory.PASTA),
    SOUP("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/suppen-rezepte-5", RecipeCategory.SOUP),
    POTATO("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/kartoffel-rezepte-6", RecipeCategory.POTATO),
    RICE("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/reis-rezepte-7", RecipeCategory.RICE),
    FISH("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/fischgerichte-8", RecipeCategory.FISH),
    PIZZA("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/pizza-9", RecipeCategory.PIZZA),
    ASPARAGUS("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/spargel-rezepte-10", RecipeCategory.ASPARAGUS),
    DESSERT("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/dessert-rezepte-11", RecipeCategory.DESSERT),
    FOOD_TRENDS("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/foodtrends-12", RecipeCategory.FOOD_TRENDS),
    FIVE_IN_FIFTEEN("https://www.hensslers-schnelle-nummer.de/schnelle-rezepte/5-zutaten-in-15-minuten-rezepte-13", RecipeCategory.FIVE_IN_FIFTEEN);

    private final String url;
    private final RecipeCategory category;

    HensslerPage(String url, RecipeCategory category) {
        this.url = url;
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public RecipeCategory getCategory() {
        return category;
    }
}
