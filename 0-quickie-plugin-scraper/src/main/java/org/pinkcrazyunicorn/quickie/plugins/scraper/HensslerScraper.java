package org.pinkcrazyunicorn.quickie.plugins.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.domain.recipe.Ingredient;
import org.pinkcrazyunicorn.quickie.domain.recipe.Quantity;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;
import org.pinkcrazyunicorn.quickie.domain.recipe.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HensslerScraper {
    private static final String BASE_URL = "https://www.hensslers-schnelle-nummer.de/";

    private static final String[] CATEGORY_PAGES = {
            "schnelle-rezepte/salat-rezepte-1",
            "schnelle-rezepte/fleischgerichte-2",
            "schnelle-rezepte/vegetarische-rezepte-3",
            "schnelle-rezepte/pasta-rezepte-4",
            "schnelle-rezepte/suppen-rezepte-5",
            "schnelle-rezepte/kartoffel-rezepte-6",
            "schnelle-rezepte/reis-rezepte-7",
            "schnelle-rezepte/fischgerichte-8",
            "schnelle-rezepte/pizza-9",
            "schnelle-rezepte/spargel-rezepte-10",
            "schnelle-rezepte/dessert-rezepte-11",
            "schnelle-rezepte/foodtrends-12",
            "schnelle-rezepte/5-zutaten-in-15-minuten-rezepte-13",
    };

    private final CachedDownloader downloader;

    public HensslerScraper() {
        super();
        this.downloader = new CachedDownloader(HensslerScraper.BASE_URL);
    }

    public Recipe getRecipeFrom(String url) throws IOException {
        Document recipePage = this.downloader.getDocument(url);
        try {
            List<Ingredient> ingredients = this.scrapeIngredients(recipePage);
            String name = this.scrapeTitle(recipePage);
            String text = this.scrapeInstructions(recipePage);

            return new Recipe(ingredients, name, text, BASE_URL + url);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error: Recipe could not be parsed. The site changed its' layout most likely");
        }
        return null;
    }

    private String scrapeTitle(Document recipePage) {
        return recipePage.selectFirst("h1").text();
    }

    private String scrapeInstructions(Document recipePage) {
        StringBuilder builder = new StringBuilder();
        Element instructionList = this.findInstructionList(recipePage);

        for (Element instruction : instructionList.children()) {
            if (!instruction.attr("class").equals("stepBox")) {
                continue;
            }
            builder.append(
                    instruction.selectFirst("div[class='text']").text()
            );
            builder.append("\n");
        }

        return builder.toString();
    }

    private Element findInstructionList(Document recipePage) {
        return recipePage.selectFirst("div[class='steps']")
                .child(0);
    }

    private List<Ingredient> scrapeIngredients(Document recipePage) {
        List<Ingredient> ingredients = new ArrayList<>();

        Element ingredientList = this.findIngredientList(recipePage);
        for (Element ingredientElement : ingredientList.children()) {
            Ingredient ingredient = this.parseIngredientText(ingredientElement.text());
            if (ingredient != null) {
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    private Ingredient parseIngredientText(String text) {
        text = text.replace(", ", "/");

        String[] parts = text.split(" ");
        if (parts.length < 3) {
            return null;
        }

        String foodString = Arrays.stream(parts).skip(2).collect(Collectors.joining(" "));
        Food food = new Food(foodString);

        double amount;
        String amountString = parts[0]
                .replace(",", ".");
        try {
            if (amountString.contains("/")) {
                String[] amountParts = amountString.split("/");
                if (amountParts.length != 2) {
                    return null;
                }
                amount = Double.parseDouble(amountParts[0]) / Double.parseDouble(amountParts[1]);
            } else if (amountString.contains("-")) {
                amount = Double.parseDouble(amountString.split("-")[0]);
            } else if (amountString.contains("+")) {
                amount = 0.0;
                for (String amountPart : amountString.split("[+]")) {
                    amount += Double.parseDouble(amountPart);
                }
            } else {
                amount = Double.parseDouble(amountString);
            }
        } catch (NumberFormatException e) {
            System.out.println("Warning: Could not convert " + amountString + " to amount in '" + text + "'");
            amount = Double.NaN;
        }


        Unit unit = new Unit(parts[1]);

        return new Ingredient(food, new Quantity(unit, amount));
    }

    private Element findIngredientList(Document recipePage) {
        return recipePage.select("div[class='ingredients']")
                .get(1)
                .child(0)
                .child(1);
    }

    public Collection<String> getRecipeURLs() {
        List<String> result = new ArrayList<>();

        for(String categoryURL : CATEGORY_PAGES) {
            try {
                Document categoryPage = this.downloader.getDocument(categoryURL, false);
                Elements recipeBoxes = categoryPage.select("div[class~=.*recipeBox .*]");
                result.addAll(
                    recipeBoxes.stream()
                            .map(box -> box
                                    .child(0)
                                    .child(0)
                                    .attributes()
                                    .get("href")
                            )
                            .collect(Collectors.toList())
                );
                Thread.sleep(200);
            } catch (IOException e) {
                System.out.println("Warning: Could not load " + categoryURL);
            } catch (InterruptedException e) {
                System.out.println("Warning: Failed to pause after request");
            }
        }

        return result;
    }
}
