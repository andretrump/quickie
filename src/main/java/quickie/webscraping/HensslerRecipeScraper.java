package quickie.webscraping;

import org.hibernate.engine.jdbc.StreamUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import quickie.db.RecipeBuilder;
import quickie.db.entities.*;
import quickie.db.exceptions.EntityNotFound;
import quickie.db.exceptions.IngredientNameExists;
import quickie.db.repositories.*;
import quickie.db.types.Size;
import quickie.db.types.Unit;

import javax.persistence.PersistenceException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HensslerRecipeScraper {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final RecipePageRepository pageRepo;
    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;
    private final IngredientUsageRepository usageRepo;
    private final StepRepository stepRepo;

    public HensslerRecipeScraper(RecipePageRepository pageRepo, RecipeRepository recipeRepo, IngredientRepository ingredientRepo, IngredientUsageRepository usageRepo, StepRepository stepRepo) {
        this.pageRepo = pageRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.usageRepo = usageRepo;
        this.stepRepo = stepRepo;
    }

    public void parseDirtyPages() {
        this.pageRepo.readDirtyPages()
                .forEach(page -> {
                    Document pageDocument = Jsoup.parse(page.getData());
                    try {
                        this.parseRecipePage(pageDocument);
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Error: Failed to parse page with url " + page.getUrl());
                        e.printStackTrace();
                    }
                    page.setDirty(false);
                    pageRepo.update(page);
                });
    }

    public Recipe parseRecipePage(Document pageDocument) {
        List<RecipeIngredient> recipeIngredients = this.parseIngredientList(
                this.extractIngredientList(pageDocument)
        );

        List<Step> steps = this.parseStepList(this.extractStepList(pageDocument));

        RecipeBuilder recipeBuilder = new RecipeBuilder();
        Recipe recipe = recipeBuilder
                .setSteps(steps)
                .setNumberIngredients(this.extractNumberIngredients(pageDocument))
                .setTitle(this.extractTitle(pageDocument))
                .setDate(this.extractDate(pageDocument))
                .setNumberPersons(this.extractNumberPersons(pageDocument))
                .setVideoLength(this.extractVideoLength(pageDocument))
                .setStars(this.extractStars(pageDocument))
                .setNumberRatings(this.extractNumberRatings(pageDocument))
                .setEnergy(this.extractEnergy(pageDocument))
                .setFat(this.extractFat(pageDocument))
                .setCarbohydrates(this.extractCarbohydrates(pageDocument))
                .setProtein(this.extractProtein(pageDocument))
                .setFibre(this.extractFibre(pageDocument))
                .build();
        this.recipeRepo.save(recipe);

        steps.forEach(step -> {
            step.setRecipe(recipe);
            this.stepRepo.update(step);
        });

        List<Ingredient> ingredients = recipeIngredients.stream()
                .map(RecipeIngredient::toIngredient)
                .collect(Collectors.toList());
        ingredients.forEach(ingredient -> {
            try {
                this.ingredientRepo.save(ingredient);
            } catch (IngredientNameExists e) {
                int index = ingredients.indexOf(ingredient);
                try {
                    ingredients.set(index, this.ingredientRepo.read(ingredient.getName()));
                } catch (EntityNotFound ne) {
                    System.out.println("Error: Unable to persist ingredient with name " + ingredient.getName());
                    ingredients.remove(index);
                    recipeIngredients.remove(index);
                }
            } catch (PersistenceException pe) {
                int index = ingredients.indexOf(ingredient);
                System.out.println("Error: Unable to persist ingredient with name " + ingredient.getName());
                ingredients.remove(index);
                recipeIngredients.remove(index);
            }
        });

        List<IngredientUsage> usages = IntStream.range(0, recipeIngredients.size())
                .mapToObj(i -> {
                    Ingredient ingredient = ingredients.get(i);
                    RecipeIngredient recipeIngredient = recipeIngredients.get(i);
                    IngredientUsageKey key = new IngredientUsageKey(recipe.getId(), ingredient.getId());
                    return new IngredientUsage(
                            key,
                            recipe,
                            ingredient,
                            recipeIngredient.getAmount(),
                            recipeIngredient.getSize(),
                            recipeIngredient.getUnit()
                    );
                })
                .collect(Collectors.toList());
        usages.forEach(this.usageRepo::update);

        return recipe;
    }

    private Element extractIngredientList(Document pageDocument) {
        return pageDocument.select("div[class='ingredients']").get(1).child(0).child(1);
    }

    private String extractIngredientText(Element listItem) {
        return listItem.text();
    }

    private List<RecipeIngredient> parseIngredientList(Element ingredientList) {
        return ingredientList.children().stream()
                .map(this::extractIngredientText)
                .map(this::parseIngredientString)
                .collect(Collectors.toList());
    }

    private RecipeIngredient parseIngredientString(String ingredientString) {
        String ingredientAmountString = ingredientString.split("\\s")[0];
        double ingredientAmount;
        try {
            ingredientAmount = Integer.parseInt(ingredientAmountString);
        } catch (NumberFormatException e) {
            // TODO: Get this right
            if (ingredientAmountString.equals("1/2")) {
                ingredientAmount = 0.5;
            } else {
                ingredientAmount = 0.25;
            }
        }
        int border = ingredientString.indexOf(" ");
        var remaining = new Object() {
            String string = ingredientString.substring(ingredientString.indexOf(" ") + 1).trim();
        };

        Optional<Size> ingredientSize = Arrays.stream(Size.values())
                .filter(size -> remaining.string.startsWith(size.getText()))
                .findFirst();
        ingredientSize.ifPresent(size -> remaining.string = remaining.string.replace(size.getText(), "").trim());

        Optional<Unit> ingredientUnit = Arrays.stream(Unit.values())
                .filter(unit -> remaining.string.startsWith(unit.getText()))
                .findFirst();
        ingredientUnit.ifPresent(unit -> remaining.string = remaining.string.replace(unit.getText(), "").trim());

        String ingredientName = remaining.string;

        return new RecipeIngredient(
                ingredientName,
                ingredientAmount,
                ingredientSize.orElse(null),
                ingredientUnit.orElse(null)
        );
    }

    private Element extractStepList(Document pageDocument) {
        return pageDocument.selectFirst("div[class='steps']").child(0);
    }

    private String extractStepText(Element stepBox) {
        return stepBox.selectFirst("div[class='text']").text();
    }

    private List<Step> parseStepList(Element stepList) {
        return stepList.children().stream()
                .filter(stepBox -> stepBox.attr("class").equals("stepBox"))
                .map(this::extractStepText)
                .map(Step::new)
                .collect(Collectors.toList());
    }

    private String extractTitle(Document pageDocument) {
        return pageDocument.selectFirst("h1").text();
    }

    private int extractNumberIngredients(Document pageDocument) {
        String number = pageDocument.selectFirst("div[class='number']").text();
        return Integer.parseInt(number);
    }

    private LocalDate extractDate(Document pageDocument) {
        String topLine = pageDocument.selectFirst("div[class='topLine']").text();
        String date = topLine.split("\\|")[1].trim().split("\\s")[2];
        return LocalDate.parse(date, HensslerRecipeScraper.dateFormat);
    }

    private int extractNumberPersons(Document pageDocument) {
        String numberPersons = pageDocument.selectFirst("div[class='personBox square']")
                .child(0)
                .child(0)
                .selectFirst("div[class='text']")
                .text();
        return Integer.parseInt(numberPersons.split("\\s")[0]);
    }

    private Duration extractVideoLength(Document pageDocument) {
        String topLine = pageDocument.selectFirst("div[class='topLine']")
                .selectFirst("span[class='noPrint']")
                .text()
                .split("\\s")[0];
        String[] lengthParts = topLine.split(":");
        try {
            long lengthSeconds = Long.parseLong(lengthParts[0]) * 60 + Long.parseLong(lengthParts[1]);
            return Duration.of(lengthSeconds, ChronoUnit.SECONDS);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int extractStars(Document pageDocument) {
        Element ratingBox = pageDocument.selectFirst("div[class~=.*ratingBox .*]");
        return (int) ratingBox.children().stream()
                .filter(ratingElement -> ratingElement.attr("class").equals("rating"))
                .count();
    }

    private int extractNumberRatings(Document pageDocument) {
        Element ratingContainer = pageDocument.selectFirst("div[class~=.*ratingContainer.*]");
        String numberString = ratingContainer
                .child(0)
                .child(0)
                .text();
        return Integer.parseInt(numberString.split("\\s")[1]);
    }

    private Element extractCircleContainer(Document pageDocument) {
        return pageDocument.selectFirst("div[class='circleContainer']");
    }

    private double extractEnergy(Document pageDocument) {
        String energyString = this.extractCircleContainer(pageDocument)
                .child(0)
                .selectFirst("span[class='valueNumber']")
                .text();
        return Double.parseDouble(energyString.substring(0, energyString.length() - 4));
    }

    private double extractFat(Document pageDocument) {
        String energyString = this.extractCircleContainer(pageDocument)
                .child(1)
                .selectFirst("span[class='valueNumber']")
                .text();
        return Double.parseDouble(energyString.substring(0, energyString.length() - 1));
    }

    private double extractCarbohydrates(Document pageDocument) {
        String energyString = this.extractCircleContainer(pageDocument)
                .child(2)
                .selectFirst("span[class='valueNumber']")
                .text();
        return Double.parseDouble(energyString.substring(0, energyString.length() - 1));
    }

    private double extractProtein(Document pageDocument) {
        String energyString = this.extractCircleContainer(pageDocument)
                .child(3)
                .selectFirst("span[class='valueNumber']")
                .text();
        return Double.parseDouble(energyString.substring(0, energyString.length() - 1));
    }

    private double extractFibre(Document pageDocument) {
        String energyString = this.extractCircleContainer(pageDocument)
                .child(4)
                .selectFirst("span[class='valueNumber']")
                .text();
        return Double.parseDouble(energyString.substring(0, energyString.length() - 1));
    }
}
