package quickie.db.exceptions;

public class InvalidPropertyValue extends Exception {
    public InvalidPropertyValue() {}

    public InvalidPropertyValue(String message) {
        super(message);
    }
}
