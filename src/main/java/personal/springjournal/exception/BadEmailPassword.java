package personal.springjournal.exception;

public class BadEmailPassword extends RuntimeException {

    public BadEmailPassword(String message) {
        super(message);
    }
}
