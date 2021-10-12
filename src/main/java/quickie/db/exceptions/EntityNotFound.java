package quickie.db.exceptions;

public class EntityNotFound extends Exception {
    public EntityNotFound() {
        super();
    }

    public EntityNotFound(String message) {
        super(message);
    }
}
