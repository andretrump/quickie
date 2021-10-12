package quickie.db.exceptions;

public class IngredientNameExists extends Exception {
    public IngredientNameExists() {
        super();
    }

    public IngredientNameExists(String message) {
        super(message);
    }
}
