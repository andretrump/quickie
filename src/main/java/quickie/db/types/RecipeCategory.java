package quickie.db.types;

public enum RecipeCategory {
    SALAD("Salat"),
    MEAT("Fleisch"),
    VEGETARIAN("Vegetarisch"),
    PASTA("Pasta"),
    SOUP("Suppe"),
    POTATO("Kartoffeln"),
    RICE("Reis"),
    FISH("Fisch"),
    PIZZA("Pizza"),
    ASPARAGUS("Spargel"),
    DESSERT("Dessert"),
    FOOD_TRENDS("Food Trends"),
    FIVE_IN_FIFTEEN("5 in 15");

    private final String text;

    RecipeCategory(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
