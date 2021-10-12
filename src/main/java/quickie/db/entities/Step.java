package quickie.db.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @Column(columnDefinition="TEXT")
    private String text;

    public Step() {}

    public Step(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(recipe, step.recipe) && Objects.equals(text, step.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, text);
    }
}
