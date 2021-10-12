package quickie.db.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "ingredient",
            orphanRemoval = true
    )
    private Set<IngredientUsage> usages;

    public Ingredient() {
        this.usages = new HashSet<>();
    }

    public Ingredient(String name) {
        this.name = name;
        this.usages = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addUsage(IngredientUsage usage) {
        this.usages.add(usage);
    }
}
