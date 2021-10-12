package quickie.db.entities;

import quickie.db.types.RecipeCategory;

import javax.persistence.*;

@Entity
@Table(name = "recipe_page")
public class RecipePage {
    @Id
    private String url;
    private RecipeCategory category;
    private boolean dirty;
    @Lob
    private String data;

    public RecipePage() {}

    public RecipePage(RecipeCategory category, String url, boolean dirty, String data) {
        this.category = category;
        this.url = url;
        this.dirty = dirty;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    public RecipeCategory getCategory() {
        return category;
    }
}
