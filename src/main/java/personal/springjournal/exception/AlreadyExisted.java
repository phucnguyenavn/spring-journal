package personal.springjournal.exception;

public class AlreadyExisted extends RuntimeException {
    public AlreadyExisted(String message) {
        super(message);
    }
}
