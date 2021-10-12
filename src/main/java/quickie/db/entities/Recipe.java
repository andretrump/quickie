package quickie.db.entities;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private Set<IngredientUsage> usages;
    private int numberIngredients;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "recipe",
            orphanRemoval = true
    )
    private Set<Step> steps;

    private String title;
    private LocalDate date;
    private int numberPersons;
    private Duration videoLength;
    private int stars;
    private int numberRatings;

    private double energy;
    private double fat;
    private double carbohydrates;
    private double protein;
    private double fibre;

    public Recipe() {}

    public Recipe(String title, int numberIngredients, LocalDate date, int numberPersons, Duration videoLength, int stars, int numberRatings, double energy, double fat, double carbohydrates, double protein, double fibre) {
        this.usages = new HashSet<>();
        this.steps = new HashSet<>();

        this.title = title;
        this.numberIngredients = numberIngredients;
        this.date = date;
        this.numberPersons = numberPersons;
        this.videoLength = videoLength;
        this.stars = stars;
        this.numberRatings = numberRatings;
        this.energy = energy;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fibre = fibre;
    }

    public void addIngredientUsage(IngredientUsage ingredient) {
        this.usages.add(ingredient);
    }

    public void removeIngredientUsage(IngredientUsage ingredient) {
        this.usages.remove(ingredient);
    }

    public Set<IngredientUsage> getIngredientUsages() {
        return usages;
    }

    public void setSteps(Set<Step> steps) {
        this.steps = steps;
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }

    public void removeStep(Step step) {
        this.steps.remove(step);
    }

    public Set<Step> getSteps() {
        return steps;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberIngredients() {
        return numberIngredients;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumberPersons() {
        return numberPersons;
    }

    public Duration getVideoLength() {
        return videoLength;
    }

    public int getStars() {
        return stars;
    }

    public int getNumberRatings() {
        return numberRatings;
    }

    public double getEnergy() {
        return energy;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    public double getFibre() {
        return fibre;
    }
}
